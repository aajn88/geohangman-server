package com.doers.geohangman.services.impl;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doers.geohangman.model.CreateChallengeImageRequest;
import com.doers.geohangman.model.CreateChallengeRequest;
import com.doers.geohangman.model.GetChallengeImageResponse;
import com.doers.geohangman.model.entities.Challenge;
import com.doers.geohangman.model.entities.ChallengeImage;
import com.doers.geohangman.repositories.IChallengeImageRepository;
import com.doers.geohangman.repositories.IChallengeRepository;
import com.doers.geohangman.services.api.IAWSService;
import com.doers.geohangman.services.api.IChallengeService;
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

	/** Challenge Repository **/
	@Autowired
	private IChallengeRepository challengeRepo;

	/** Challenge Image Repository **/
	@Autowired
	private IChallengeImageRepository imageRepo;

	/** AWS Service **/
	@Autowired
	private IAWSService awsService;

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
		Challenge challenge = new Challenge();
		challenge.setChallengerId(request.getChallengerId());
		challenge.setOpponentId(request.getOpponentId());
		challenge.setWord(request.getWord());
		challenge.setLat(request.getLat());
		challenge.setLng(request.getLng());
		challenge.setZoom(request.getZoom());
		challenge = challengeRepo.save(challenge);
		return challenge.getId();
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
	public String createChallengeImage(CreateChallengeImageRequest request)
			throws IOException {

		// TODO: Validate all fields

		Challenge challenge = findChallengeById(request.getChallengeId());

		Validate.notNull(challenge,
				"Challenge has not been found. The challenge does not exist");

		String imageUrl = uploadPic(request);
		
		ChallengeImage challengeImage = new ChallengeImage();
		challengeImage.setChallengeId(challenge.getId());
		challengeImage.setImageUrl(imageUrl);
		imageRepo.save(challengeImage);
		
		challenge.setImageId(challengeImage.getId());
		challengeRepo.save(challenge);

		return imageUrl;
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
	private String uploadPic(CreateChallengeImageRequest request)
			throws IOException {
		String fileName = storeImageInServer(request);
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
	private String storeImageInServer(CreateChallengeImageRequest request)
			throws IOException {
		byte[] decodedImage = Base64.decodeBase64(request.getImageBytes());
		String fileName = StorageUtils.generateFileName();
		StorageUtils.storeFile(fileName, JPG_FORMAT, decodedImage);
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

}
