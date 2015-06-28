package com.doers.geohangman.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.doers.geohangman.model.CreateChallengeImageRequest;
import com.doers.geohangman.model.CreateChallengeImageResponse;
import com.doers.geohangman.model.CreateChallengeRequest;
import com.doers.geohangman.model.CreateChallengeResponse;
import com.doers.geohangman.model.GetChallengeImageResponse;
import com.doers.geohangman.model.entities.Challenge;
import com.doers.geohangman.services.api.IChallengeService;

/**
 * 
 * This is the challenges controller
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@RestController
@RequestMapping(value = "/challenges")
public class ChallengesController {
	
	/** LOGGER **/
	private static final Logger LOGGER = LoggerFactory.getLogger(ChallengesController.class);

	/** The Challenge Service **/
	@Autowired
	private IChallengeService challengeService;

	/**
	 * Returns a challenge given an Id
	 * 
	 * @param id
	 * @return Challenge if it was found, otherwise returns null
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Challenge getChallenge(@PathVariable Integer id) {
		return challengeService.findChallengeById(id);
	}

	/**
	 * Creates a Challenge given an specific request
	 * 
	 * @param request
	 * @return CreateChallengeResponse with new challenge Id
	 */
	@RequestMapping(method = RequestMethod.POST)
	public CreateChallengeResponse createChallenge(
			@RequestBody CreateChallengeRequest request) {
		LOGGER.debug("Create challenge request received");
		Integer id = challengeService.createChallenge(request);
		CreateChallengeResponse response = new CreateChallengeResponse();
		response.setChallengeId(id);

		return response;
	}

	/**
	 * Returns a Challenge Image given a challenge Id
	 * 
	 * @param id
	 *            The challenge Id
	 * @return Challenge Image if exists, otherwise returns null
	 */
	@RequestMapping(value = "/{challengeId}/image", method = RequestMethod.GET)
	public GetChallengeImageResponse getChallengeImageByChallengeId(
			@PathVariable Integer challengeId) {
		return challengeService.findChallengeImageByChallengeId(challengeId);
	}

	/**
	 * This service creates an image given a challengeId and the image bytes
	 * (included into CreateChallengeImageRequest object)
	 * 
	 * @param request
	 *            with all challenge image information
	 * @return CreateChallengeImageResponse with all created image information
	 *         as its Id
	 */
	@RequestMapping(value = "/images", method = RequestMethod.POST)
	public CreateChallengeImageResponse createChallengeImage(
			@RequestBody CreateChallengeImageRequest request) {
		LOGGER.info("Create image challenge for challengeId = {}", request.getChallengeId());
		Integer imageId = challengeService.createChallengeImage(request);
		CreateChallengeImageResponse response = new CreateChallengeImageResponse();
		response.setChallengeImageId(imageId);

		return response;
	}

}
