package com.doers.geohangman.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
