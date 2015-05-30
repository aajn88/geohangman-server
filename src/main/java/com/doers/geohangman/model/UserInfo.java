package com.doers.geohangman.model;

import java.util.List;

/**
 * 
 * This is User's basic info
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public class UserInfo {

	/** User's Google Id (GeoHangman Id) **/
	private String id;

	/** User's name **/
	private String name;

	/** User's email **/
	private String email;

	/** User's friends **/
	private List<UserInfo> friends;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the friends
	 */
	public List<UserInfo> getFriends() {
		return friends;
	}

	/**
	 * @param friends
	 *            the friends to set
	 */
	public void setFriends(List<UserInfo> friends) {
		this.friends = friends;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", email=" + email
				+ "]";
	}

}
