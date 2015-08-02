package com.doers.geohangman.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.doers.geohangman.constants.ResponseCode;
import com.doers.geohangman.model.entities.Challenge;
import com.doers.geohangman.model.restful.CreateChallengeImageResponse;
import com.doers.geohangman.model.restful.CreateChallengeRequest;
import com.doers.geohangman.model.restful.CreateChallengeResponse;
import com.doers.geohangman.model.restful.GetChallengeImageResponse;
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
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ChallengesController.class);

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
	 * This service creates an image given a challengeId and the image bytes (as
	 * a multipart file)
	 * 
	 * @param challengeId The challenge Id, owner of the image
	 * @param pic Challenge's pic
	 * @return Upload response
	 */
	@RequestMapping(value = "/{challengeId}/image", method = RequestMethod.POST)
	public @ResponseBody CreateChallengeImageResponse createChallengeImage(
			@PathVariable int challengeId,
			@RequestParam("pic") MultipartFile pic) {
		LOGGER.debug("Receiving Challenge's pic for challenge [{}]", challengeId);
		CreateChallengeImageResponse response = new CreateChallengeImageResponse();
		if (!pic.isEmpty()) {
			try {
				byte[] picBytes = pic.getBytes();
				String imageUrl = challengeService.createChallengeImage(
						challengeId, picBytes);
				response.setImageUrl(imageUrl);
			} catch (Exception e) {
				LOGGER.error(
						"An error has occurred while uploading files to WSA", e);
				response.setResponseCode(ResponseCode.ERROR);
			}
		}

		return response;
	}
}
