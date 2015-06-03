package com.doers.geohangman.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doers.geohangman.model.entities.User;
import com.doers.geohangman.repositories.IUserRepository;
import com.doers.geohangman.services.api.IUserService;

/**
 * 
 * UserInfo service implementation of {@link IUserService}
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@Service
public class UserService implements IUserService {
	
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
	
}
