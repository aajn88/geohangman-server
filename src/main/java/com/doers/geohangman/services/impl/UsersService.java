package com.doers.geohangman.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doers.geohangman.model.entities.User;
import com.doers.geohangman.repositories.IUserRepository;
import com.doers.geohangman.services.api.IUsersService;
import com.doers.geohangman.utils.UserUtils;

/**
 * 
 * UserInfo service implementation of {@link IUsersService}
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@Service
public class UsersService implements IUsersService {

	/** The UserInfo Repository **/
	@Autowired
	private IUserRepository userRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.doers.geohangman.services.api.IUserService#createUser(com.doers.
	 * geohangman.model.entities.UserInfo)
	 */
	@Override
	@Transactional
	public String createUser(User user) {
		completeUserFriends(user);
		User foundUser = findUserById(user.getId());
		if(foundUser != null) {
			user.setToken(foundUser.getToken());
		}
		user = userRepo.save(user);
		return user.getId();
	}

	/**
	 * This method completes user's friends information. Therefore, the
	 * information will not be override with wrong data
	 * 
	 * @param user
	 */
	private void completeUserFriends(User user) {
		List<User> friends = user.getFriends();
		if (friends == null) {
			return;
		}

		for (User friend : friends) {
			User realUser = findUserById(friend.getId());
			UserUtils.copyUserInformation(realUser, friend, Boolean.FALSE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.api.IUserService#findUserById(java.lang
	 * .String)
	 */
	@Override
	public User findUserById(String id) {
		return userRepo.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.api.IUserService#findFriendsById(java.lang
	 * .String)
	 */
	@Override
	public List<User> findFriendsById(String id) {
		User user = findUserById(id);
		return (user == null ? null : user.getFriends());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.api.IUsersService#findRegisteredFriendsById
	 * (java.lang.String)
	 */
	@Override
	public List<User> findRegisteredFriendsById(String id) {
		return userRepo.findRegisteredFriendsById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.api.IUsersService#createFriends(java.lang
	 * .String, java.util.List)
	 */
	@Override
	public String createFriends(String id, List<User> friends) {
		User user = findUserById(id);
		Validate.notNull(user, "The User [{}] does not exist", id);
		user.setFriends(friends);
		return createUser(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.doers.geohangman.services.api.IUsersService#updateUsersToken(java
	 * .lang.String, java.lang.String)
	 */
	@Override
	public String updateUsersToken(String id, String token) {
		User user = findUserById(id);
		Validate.notNull(user, "The User [" + id + "] does not exist");
		user.setToken(token);
		return createUser(user);
	}

}
