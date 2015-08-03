package com.doers.geohangman.services.impl;

import java.io.IOException;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.doers.geohangman.model.entities.Challenge;
import com.doers.geohangman.model.entities.ChallengeImage;
import com.doers.geohangman.model.entities.User;
import com.doers.geohangman.model.restful.BasicGcmRequest;
import com.doers.geohangman.model.restful.BasicGcmResponse;
import com.doers.geohangman.model.restful.CreateChallengeRequest;
import com.doers.geohangman.model.restful.GetChallengeImageResponse;
import com.doers.geohangman.model.restful.Notification;
import com.doers.geohangman.repositories.IChallengeImageRepository;
import com.doers.geohangman.repositories.IChallengeRepository;
import com.doers.geohangman.services.api.IAWSService;
import com.doers.geohangman.services.api.IChallengeService;
import com.doers.geohangman.services.api.IUsersService;
import com.doers.geohangman.utils.RestUtils;
import com.doers.geohangman.utils.StorageUtils;

/**
 * 
 * This class implements {@link IChallengeService} to offer Challenge's main
 * services
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@Service
public class ChallengeService implements IChallengeService {

	/** Logger **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ChallengeService.class);

	/** Challenge Repository **/
	@Autowired
	private IChallengeRepository challengeRepo;

	/** Challenge Image Repository **/
	@Autowired
	private IChallengeImageRepository imageRepo;

	/** AWS Service **/
	@Autowired
	private IAWSService awsService;

	/** Users Service **/
	@Autowired
	private IUsersService usersService;

	/** JPG Format **/
	private static final String JPG_FORMAT = ".jpg";

	/** Geohangman Bucket to be used **/
	private static final String GEOHANGMAN_BUCKET = "geohangman";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.api.IChallengeService#createChallenge(com
	 * .doers.geohangman.model.entities.Challenge)
	 */
	@Override
	public Integer createChallenge(CreateChallengeRequest request) {
		Challenge challenge = parseChallenge(request);
		challenge = challengeRepo.save(challenge);
		return challenge.getId();
	}

	/**
	 * This method parses Challenge request to server challenge
	 * 
	 * @param request
	 *            Challenge request
	 * @return Server Challenge
	 */
	private Challenge parseChallenge(CreateChallengeRequest request) {
		Challenge challenge = new Challenge();
		challenge.setChallengerId(request.getChallengerId());
		challenge.setOpponentId(request.getOpponentId());
		challenge.setWord(request.getWord());
		challenge.setLat(request.getLat());
		challenge.setLng(request.getLng());
		challenge.setZoom(request.getZoom());
		return challenge;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.api.IChallengeService#findChallengeById
	 * (java.lang.Integer)
	 */
	@Override
	public Challenge findChallengeById(Integer id) {
		return challengeRepo.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.api.IChallengeService#createChallengeImage
	 * (com.doers.geohangman.model.entities.ChallengeImage)
	 */
	@Override
	public String createChallengeImage(int challengeId, byte[] picBytes)
			throws IOException {

		// TODO: Validate all fields

		Challenge challenge = findChallengeById(challengeId);

		Validate.notNull(challenge,
				"Challenge has not been found. The challenge does not exist");

		LOGGER.debug(
				"Uploading image to amazon's WSA for challenge [{}]. Image size: {} bytes",
				challengeId, picBytes.length);
		String imageUrl = uploadPic(picBytes);

		LOGGER.debug("Saving challenge [{}]", challengeId);
		ChallengeImage challengeImage = new ChallengeImage();
		challengeImage.setChallengeId(challenge.getId());
		challengeImage.setImageUrl(imageUrl);
		imageRepo.save(challengeImage);

		challenge.setImageId(challengeImage.getId());
		challengeRepo.save(challenge);

		notifyOpponent(challenge);

		return imageUrl;
	}

	/**
	 * This method notifies Opponent about challenge
	 * 
	 * @param challenge
	 *            Challenge to be notified
	 */
	private void notifyOpponent(Challenge challenge) {
		LOGGER.debug(
				"Notifying challenge [{}] from challenger [{}] to opponent [{}]",
				challenge.getId(), challenge.getChallengerId(),
				challenge.getOpponentId());
		BasicGcmRequest<GcmData> request = buildGcmChallengeNotification(challenge);

		BasicGcmResponse response = RestUtils.exchange(
				"https://gcm-http.googleapis.com/gcm/send",
				BasicGcmRequest.class, request, BasicGcmResponse.class,
				HttpMethod.POST, true);

		LOGGER.debug("GCM Response: [{}]", response);
	}

	/**
	 * @param challenge
	 * @return
	 */
	private BasicGcmRequest<GcmData> buildGcmChallengeNotification(
			Challenge challenge) {
		BasicGcmRequest<GcmData> request = new BasicGcmRequest<ChallengeService.GcmData>();
		User challenger = usersService
				.findUserById(challenge.getChallengerId());
		User opponent = usersService.findUserById(challenge.getOpponentId());

		Notification notification = new Notification();
		notification.setTitle("Geohangman Challenge!");
		notification.setText(String.format(
				"You've recieved a challenge from %s", challenger.getName()));

		GcmData data = new GcmData();
		data.setChallengerId(challenge.getChallengerId());
		data.setChallengerName(challenger.getName());
		data.setChallengeId(challenge.getId());

		request.setNotification(notification);
		request.setData(data);

		request.setTo(opponent.getToken());
		return request;
	}

	/**
	 * This method uploads a picture in a given request to AWS Servers. If
	 * process is successful, then returns the AWS URL
	 * 
	 * @param request
	 *            Where image is located
	 * @return The AWS URL
	 * @throws IOException
	 */
	private String uploadPic(byte[] picBytes) throws IOException {
		String fileName = storeImageInServer(picBytes);
		return uploadFile(fileName, Boolean.TRUE);
	}

	/**
	 * This method uploads a File into AWS Servers given a file stored into this
	 * server. The user could define wheter this file is erased after process or
	 * not
	 * 
	 * @param fileName
	 *            File name to be uploaded into AWS Servers
	 * @param eraseFile
	 *            Whether the file is erased after process or not
	 * @return AWS URL where file is located
	 */
	private String uploadFile(String fileName, boolean eraseFile) {
		String awsUrl = awsService.uploadFile(fileName, GEOHANGMAN_BUCKET,
				fileName);

		StorageUtils.deleteFile(fileName);

		return awsUrl;
	}

	/**
	 * 
	 * This method stores image into server and returns its fileName
	 * 
	 * @param request
	 *            Contains image to be stored
	 * @return File name
	 * @throws IOException
	 */
	private String storeImageInServer(byte[] picBytes) throws IOException {
		String fileName = StorageUtils.generateFileName();
		StorageUtils.storeFile(fileName, JPG_FORMAT, picBytes);
		return new StringBuilder(fileName).append(JPG_FORMAT).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.api.IChallengeService#findChallengeImageById
	 * (java.lang.Integer)
	 */
	@Override
	public ChallengeImage findChallengeImageById(Integer id) {
		return imageRepo.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.doers.geohangman.services.api.IChallengeService#
	 * findChallengeImageByChallengeId(java.lang.Integer)
	 */
	@Override
	public GetChallengeImageResponse findChallengeImageByChallengeId(
			Integer challengeId) {
		Challenge challenge = findChallengeById(challengeId);

		GetChallengeImageResponse response = null;

		if (challenge != null) {
			response = new GetChallengeImageResponse();
			ChallengeImage image = findChallengeImageById(challenge
					.getImageId());
			response.setImageId(image.getId());
			response.setImageUrl(image.getImageUrl());
		}

		return response;
	}

	public class GcmData {

		/** Challenger Id **/
		private String challengerId;

		/** Challenger's Name **/
		private String challengerName;

		/** Challenge Id **/
		private Integer challengeId;

		/**
		 * @return the challengerId
		 */
		public String getChallengerId() {
			return challengerId;
		}

		/**
		 * @param challengerId
		 *            the challengerId to set
		 */
		public void setChallengerId(String challengerId) {
			this.challengerId = challengerId;
		}

		/**
		 * @return the challengerName
		 */
		public String getChallengerName() {
			return challengerName;
		}

		/**
		 * @param challengerName
		 *            the challengerName to set
		 */
		public void setChallengerName(String challengerName) {
			this.challengerName = challengerName;
		}

		/**
		 * @return the challengeId
		 */
		public Integer getChallengeId() {
			return challengeId;
		}

		/**
		 * @param challengeId
		 *            the challengeId to set
		 */
		public void setChallengeId(Integer challengeId) {
			this.challengeId = challengeId;
		}

	}

}
