package com.doers.geohangman.services.api;

import java.util.List;

import com.doers.geohangman.model.entities.User;

/**
 * UserInfo service interface
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public interface IUsersService {
	
	/**
	 * Creates User
	 * 
	 * @param user the user to be created
	 * @return User Id
	 */
	String createUser(User user);
	
	/**
	 * Finds a User by a given Id
	 * 
	 * @param id
	 * @return the user if it was found, otherwise returns null
	 */
	User findUserById(String id);
	
	/**
	 * Returns friends list given a User Id
	 * 
	 * @param id The user id
	 * @return List of friends
	 */
	List<User> findFriendsById(String id);
	
	/**
	 * This method creates User's friends
	 * 
	 * @param id Id of the User's friends
	 * @param friends User's friends
	 * @return User's id if process was successful, otherwise, returns null
	 */
	String createFriends(String id, List<User> friends);
	
}
