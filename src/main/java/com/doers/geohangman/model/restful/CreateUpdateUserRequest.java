package com.doers.geohangman.model.restful;

import com.doers.geohangman.model.entities.User;

/**
 * Create or Update User request
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public class CreateUpdateUserRequest extends AbstractRequest {

	/** User to be created/updated **/
	private User user;

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
