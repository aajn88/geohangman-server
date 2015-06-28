package com.doers.geohangman.services.impl;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doers.geohangman.model.CreateChallengeImageRequest;
import com.doers.geohangman.model.CreateChallengeRequest;
import com.doers.geohangman.model.GetChallengeImageResponse;
import com.doers.geohangman.model.entities.Challenge;
import com.doers.geohangman.model.entities.ChallengeImage;
import com.doers.geohangman.repositories.IChallengeImageRepository;
import com.doers.geohangman.repositories.IChallengeRepository;
import com.doers.geohangman.services.api.IChallengeService;

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
	public Integer createChallengeImage(CreateChallengeImageRequest request) {
		
		// TODO: Validate all fields
		
		Challenge challenge = findChallengeById(request.getChallengeId());
		
		ChallengeImage image = new ChallengeImage();
		image.setChallengeId(request.getChallengeId());
		image.setImage(request.getImageBytes().getBytes());
		image = imageRepo.save(image);
		
		Integer imageId = image.getId();
		challenge.setImageId(imageId);
		challengeRepo.save(challenge);
		
		return imageId;
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
	public GetChallengeImageResponse findChallengeImageByChallengeId(Integer challengeId) {
		Challenge challenge = findChallengeById(challengeId);
		
		GetChallengeImageResponse response = null;
		
		if(challenge != null) {
			response = new GetChallengeImageResponse();
			ChallengeImage image = findChallengeImageById(challenge.getImageId());
			response.setImageId(image.getId());
			response.setImageBytes(Base64.encodeBase64String(image.getImage()));
		}

		return response;
	}

}
