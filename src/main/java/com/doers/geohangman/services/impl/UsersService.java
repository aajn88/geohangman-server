package com.doers.geohangman.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doers.geohangman.model.entities.User;
import com.doers.geohangman.repositories.IUserRepository;
import com.doers.geohangman.services.api.IUsersService;

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

	/* (non-Javadoc)
	 * @see com.doers.geohangman.services.api.IUserService#createUser(com.doers.geohangman.model.entities.UserInfo)
	 */
	@Override
	@Transactional
	public String createUser(User user) {
		user = userRepo.save(user);
		return user.getId();
	}

	/* (non-Javadoc)
	 * @see com.doers.geohangman.services.api.IUserService#findUserById(java.lang.String)
	 */
	@Override
	public User findUserById(String id) {
		return userRepo.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.doers.geohangman.services.api.IUserService#findFriendsById(java.lang.String)
	 */
	@Override
	public List<User> findFriendsById(String id) {
		User user = findUserById(id);
		return (user == null ? null : user.getFriends());
	}

	/* (non-Javadoc)
	 * @see com.doers.geohangman.services.api.IUsersService#createFriends(java.lang.String, java.util.List)
	 */
	@Override
	public String createFriends(String id, List<User> friends) {
		User user = findUserById(id);
		user.setFriends(friends);
		return createUser(user);
	}
	
}
