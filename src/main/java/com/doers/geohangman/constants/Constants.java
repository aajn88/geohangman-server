package com.doers.geohangman.constants;

/**
 * Geohangman Constants
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public final class Constants {

	/** GeoHangman main package **/
	public static final String GEOHANGMAN_MAIN_PACKAGE = "com.doers.geohangman";
	
	/** The Geohangman Bucket on AWS **/
	public static final String AWS_GEOHANGMAN_BUCKET = "geohangman";
	
	/** The AWS Access Key environment variable **/
	public static final String AWS_ACCESS_KEY_ID = "AWS_ACCESS_KEY_ID";
	
	/** The AWS Secret Access Key environment variable **/
	public static final String AWS_SECRET_ACCESS_KEY = "AWS_SECRET_ACCESS_KEY";
	
	/** The AWS URL Prefix **/
	public static final String AWS_URL_PREFIX = "https://s3-us-west-2.amazonaws.com/";

	/** Entities Package **/
	public static final String ENTITIES_PACKAGE = "com.doers.geohangman.model.entities";

	/** Repositories Package **/
	public static final String REPOSITORIES_PACKAGE = "com.doers.geohangman.repositories";
	
	/** Hibernate Format SQL property **/
	public static final String HIBERNATE_FORMAT_SQL_PROPERTY = "hibernate.format_sql";

	/** Hibernate Show SQL property **/
	public static final String HIBERNATE_SHOW_SQL_PROPERTY = "hibernate.show_sql";

	/** Hibernate EJB Naming Strategy property **/
	public static final String HIBERNATE_EJB_NAMING_STRATEGY_PROPERTY = "hibernate.ejb.naming_strategy";

	/** Hibernate HBM2DDL Auto property **/
	public static final String HIBERNATE_HBM2DDL_AUTO_PROPERTY = "hibernate.hbm2ddl.auto";

	/** Hibernate Dialect property **/
	public static final String HIBERNATE_DIALECT_PROPERTY = "hibernate.dialect";

	/** DB Url prefix **/
	public static final String DB_URL_PREFIX = "jdbc:postgresql://";

	/** Datadase System Env. variable **/
	public static final String DATABASE_URL_ENV_VAR = "DATABASE_URL";
	
	/** App ApiKey System Env. variable **/
	public static final String APP_API_KEY_ENV_VAR = "API_KEY";

	/**
	 * Privat4e default constructor
	 */
	private Constants() {
	}

}
