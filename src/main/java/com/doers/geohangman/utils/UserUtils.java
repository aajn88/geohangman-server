package com.doers.geohangman.utils;

import com.doers.geohangman.model.entities.User;

/**
 * Utils class for User class
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public final class UserUtils {

	/** Private default constructor **/
	private UserUtils() {
	}

	/**
	 * This method copies all User information from a User to another User
	 * 
	 * @param from
	 *            User to be copied
	 * @param to
	 *            User target where information will be replaced
	 */
	public static void copyUserInformation(User from, User to) {
		copyUserInformation(from, to, Boolean.TRUE);
	}

	/**
	 * This method copies User information from a User to another User. Argument
	 * copyFriends determines if friends are copied
	 * 
	 * @param from
	 *            User to be copied
	 * @param to
	 *            User target where information will be replaced
	 * @param copyFriends
	 *            Determines if friends must be copied or not
	 */
	public static void copyUserInformation(User from, User to,
			boolean copyFriends) {
		if (from == null || to == null) {
			throw new IllegalArgumentException(
					"Neither user from and user to could be null");
		}

		to.setId(from.getId());
		to.setName(from.getName());
		to.setEmail(from.getEmail());
		if (copyFriends) {
			to.setFriends(from.getFriends());
		}
	}
}
