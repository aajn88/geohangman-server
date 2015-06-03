package com.doers.geohangman.repositories;

import org.springframework.data.repository.CrudRepository;

import com.doers.geohangman.model.entities.ChallengeImage;

/**
 * {@link ChallengeImage} repository
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public interface IChallengeImageRepository extends CrudRepository<ChallengeImage, Integer> {
	
	/**
	 * Finds a challenge image by its Id
	 * 
	 * @param id the challenge Id
	 * @return ChallengeImage associated to this challengeId
	 */
	ChallengeImage findByChallengeId(Integer id);
	
}
