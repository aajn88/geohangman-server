package com.doers.geohangman.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.doers.geohangman.model.entities.User;

/**
 * UserInfo repository
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public interface IUserRepository extends CrudRepository<User, String> {
	
	/**
	 * Find UserInfo by Email
	 * 
	 * @param email the user's email
	 * @return list of UsersEmail
	 */
	List<User> findByEmail(String email);
	
}
