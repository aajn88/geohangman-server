package com.doers.geohangman.services.api;

import java.io.IOException;

import com.doers.geohangman.model.entities.Challenge;
import com.doers.geohangman.model.entities.ChallengeImage;
import com.doers.geohangman.model.restful.CreateChallengeImageRequest;
import com.doers.geohangman.model.restful.CreateChallengeRequest;
import com.doers.geohangman.model.restful.GetChallengeImageResponse;

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
	 * @param request
	 *            that includes all challenge information
	 * @return The challenge Id or null if store process failed
	 */
	Integer createChallenge(CreateChallengeRequest request);

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
	 * @param request
	 *            with all image information
	 * 
	 * @return Picture URL in AWS Servers
	 * @throws IOException 
	 */
	String createChallengeImage(CreateChallengeImageRequest request) throws IOException;

	/**
	 * Finds a challenge image by its Id
	 * 
	 * @param id
	 *            The challenge image Id
	 * @return A {@link ChallengeImage} if exists, otherwise returns null
	 */
	ChallengeImage findChallengeImageById(Integer id);

	/**
	 * Finds a Challenge Image given the Challenge Id
	 * 
	 * @param challengeId
	 *            The Challenge Id
	 * @return GetChallengeImageResponse if exists, otherwise returns null
	 */
	GetChallengeImageResponse findChallengeImageByChallengeId(Integer challengeId);

}
