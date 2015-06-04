package com.doers.geohangman.model;

import java.util.List;

import com.doers.geohangman.model.entities.User;

/**
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public class CreateUpdateFriendsRequest extends AbstractRequest {

	/** User's Friends **/
	private List<User> friends;

	/**
	 * @return the friends
	 */
	public List<User> getFriends() {
		return friends;
	}

	/**
	 * @param friends
	 *            the friends to set
	 */
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

}
