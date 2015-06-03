package com.doers.geohangman.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Integer createChallenge(Challenge challenge) {
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
	public Integer createChallengeImage(ChallengeImage image) {
		image = imageRepo.save(image);
		return image.getId();
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

}
