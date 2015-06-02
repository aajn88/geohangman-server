package com.doers.geohangman.services.api;

import com.doers.geohangman.model.entities.User;

/**
 * UserInfo service interface
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public interface IUserService {
	
	/**
	 * Creates User
	 * 
	 * @param user the user to be created
	 */
	void createUser(User user);
	
	/**
	 * Finds a User by a given Id
	 * 
	 * @param id
	 * @return the user if it was found, otherwise returns null
	 */
	User findUserById(String id);
	
}
