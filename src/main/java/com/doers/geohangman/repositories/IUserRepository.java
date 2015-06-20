package com.doers.geohangman.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.doers.geohangman.constants.ModelConstants;
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
	 * @param email
	 *            the user's email
	 * @return list of UsersEmail
	 */
	List<User> findByEmail(String email);

	/**
	 * Finds all registered friends given a User Id
	 * 
	 * @param id The User Id
	 * @return List of all registered friends in Geohangman systems
	 */
	@Query(nativeQuery = true, value = ModelConstants.USER_FRIENDS_NATIVE_QUERY)
	List<User> findRegisteredFriendsById(@Param("id") String id);

}
