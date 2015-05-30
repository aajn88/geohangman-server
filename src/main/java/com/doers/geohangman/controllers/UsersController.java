package com.doers.geohangman.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doers.geohangman.model.CreateUpdateUserRequest;

/**
 * This controller represents User resource
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UsersController {
	
	/** Logger **/
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getUser(@RequestParam(value = "id", required = true) String id) {
		LOGGER.debug("Get user: " + id);
		return id;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void create(@RequestBody CreateUpdateUserRequest request) {
		LOGGER.debug("Creating user: " + request.getUser());
	}
	
}
