package com.doers.geohangman.services.api;

import com.doers.geohangman.model.entities.Challenge;
import com.doers.geohangman.model.entities.ChallengeImage;

/**
 * 
 * Challenges Service Interface
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public interface IChallengeService {

	/**
	 * This method creates a challenge and return its Id
	 * 
	 * @param challenge
	 *            to be stored
	 * @return The challenge Id or null if store process failed
	 */
	Integer createChallenge(Challenge challenge);

	/**
	 * Finds a challenge by its Id
	 * 
	 * @param id
	 *            The challenge Id
	 * @return A {@link Challenge} if exists, otherwise returns null
	 */
	Challenge findChallengeById(Integer id);

	/**
	 * This method creates the challenge Image and return its Id
	 * 
	 * @param image
	 *            to be created
	 * @return Challenge Image Id
	 */
	Integer createChallengeImage(ChallengeImage image);

	/**
	 * Finds a challenge image by its Id
	 * 
	 * @param id
	 *            The challenge image Id
	 * @return A {@link ChallengeImage} if exists, otherwise returns null
	 */
	ChallengeImage findChallengeImageById(Integer id);

}
