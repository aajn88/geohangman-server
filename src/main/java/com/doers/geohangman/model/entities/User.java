package com.doers.geohangman.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * This is User's basic info Entity
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@Entity
@Table(name = "user", schema = "geohangman")
public class User {

	/** User's Google Id (GeoHangman Id) **/
	@Id
	private String id;

	/** User's name **/
	@NotNull
	private String name;

	/** User's email **/
	@NotNull
	private String email;

	/** User's friends **/
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "friends", schema = "geohangman",  joinColumns = {
			@JoinColumn(name = "main_user_id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "friend_id")
	})
	private List<User> friends;

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
