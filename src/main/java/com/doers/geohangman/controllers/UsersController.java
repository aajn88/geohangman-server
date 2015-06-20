package com.doers.geohangman.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.doers.geohangman.model.CreateUpdateFriendsRequest;
import com.doers.geohangman.model.CreateUpdateUserRequest;
import com.doers.geohangman.model.entities.User;
import com.doers.geohangman.services.api.IUsersService;
import com.doers.geohangman.services.api.IValidationService;

/**
 * This controller represents User resource
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@RestController
@RequestMapping(value = "/users")
public class UsersController {

	/** Logger **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UsersController.class);

	/** The User Service **/
	@Autowired
	private IUsersService usersService;

	/** The Validation Service **/
	@Autowired
	private IValidationService validationService;

	/**
	 * This GET method requests a User given his {@code id}
	 * 
	 * @param id
	 *            the User Id
	 * @return the User or null if he does not exist
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable String id) {
		LOGGER.debug("Get User [{}] has been requested", id);
		return usersService.findUserById(id);
	}

	/**
	 * Request method for User's friends.
	 * 
	 * Returns a List of Users given a User id
	 * 
	 * @param id
	 *            The user Id
	 * @return returns a list of Users representing the User's friends. If the
	 *         User does not exist, returns null
	 */
	@RequestMapping(value = "/{id}/friends", method = RequestMethod.GET)
	public List<User> getFriendsList(@PathVariable String id) {
		LOGGER.debug("Friends list requested for User [{}]", id);
		return usersService.findFriendsById(id);
	}
	
	/**
	 * Request for registered friends of a User
	 * 
	 * @param id The user Id
	 * @return List of User's friends that are registered in Geohangman
	 */
	@RequestMapping(value = "/{id}/registered-friends", method = RequestMethod.GET)
	public List<User> getRegisteredFriendsList(@PathVariable String id) {
		LOGGER.debug("Registered friends list requested for User [{}]", id);
		return usersService.findRegisteredFriendsById(id);
	}
	
	@RequestMapping(value = "/{id}/friends", method = RequestMethod.POST)
	public String postFriendsList(@PathVariable String id, @RequestBody CreateUpdateFriendsRequest request) {
		LOGGER.debug("Creating friends for User [{}]", id);
		validationService.authenticate(request);
		return usersService.createFriends(id, request.getFriends());
	}

	/**
	 * This POST method creates a new User
	 * 
	 * @param request
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String createUser(@RequestBody CreateUpdateUserRequest request) {
		LOGGER.debug("Create User request [{}]", request.getUser());
		validationService.authenticate(request);
		return usersService.createUser(request.getUser());
	}

}
