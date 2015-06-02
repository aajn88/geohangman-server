package com.doers.geohangman.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.doers.geohangman.model.CreateUpdateUserRequest;
import com.doers.geohangman.model.entities.User;
import com.doers.geohangman.services.api.IUserService;
import com.doers.geohangman.services.api.IValidationService;

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
	
	/** The User Service **/
	@Autowired
	private IUserService userService;
	
	/** The Validation Service **/
	@Autowired
	private IValidationService validationService;
	
	/**
	 * This GET method requests a User given his {@code id}
	 * 
	 * @param id the User Id
	 * @return the User or null if he does not exist
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable String id) {
		LOGGER.debug("Get user [{}] has been requested", id);
		return userService.findUserById(id);
	}
	
	/**
	 * This POST method creates a new User
	 * @param request
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void create(@RequestBody CreateUpdateUserRequest request) {
		LOGGER.debug("Create user request [{}]", request.getUser());
		validationService.authenticate(request);
		userService.createUser(request.getUser());
	}
	
}
