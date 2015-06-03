/**
 * 
 */
package com.doers.geohangman.constants;

/**
 * 
 * This class contains all Database Model constants
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public final class ModelConstants {
	
	/** TABLES **/
	
	/** Default DB Schema **/
	public final static String DEFAULT_SCHEMA = "geohangman";
	
	/** Users Table **/
	public final static String USERS_TABLE = "geohangman_users";
	
	/** Users Table **/
	public final static String FRIENDS_TABLE = "users_friends";
	
	/** Challenge Table **/
	public final static String CHALLENGES_TABLE = "geohangman_challenges";
	
	/** Images Table **/
	public final static String CHALLENGES_IMAGES_TABLE = "challenges_images";
	
	/** FIELDS **/
	
	/** Users Table **/
	public final static String FRIENDS_TABLE_MAIN_USER_ID = "main_user_id";
	
	/** Users Table **/
	public final static String FRIENDS_TABLE_FRIEND_ID = "friend_id";

	/**
	 * Private default constructor
	 */
	private ModelConstants() {
	}

}
