package com.doers.geohangman.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import liquibase.integration.spring.SpringLiquibase;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.doers.geohangman.constants.Constants;

/**
 * Configuration class for GeoHangman Application
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@SpringBootApplication
@ComponentScan("com.doers.geohangman")
@PropertySource("geohangman.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { Constants.REPOSITORIES_PACKAGE })
public class Application {

	/** DB Url separator **/
	public static final String SEPARATOR = ":";

	/**
	 * Main method for app
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * Database Bean for configuration
	 * 
	 * @return basicDataSource with DB information
	 * @throws URISyntaxException
	 *             if uri of DATABASE System env. was malformed
	 */
	@Bean
	public BasicDataSource dataSource() throws URISyntaxException {
		URI dbUri = new URI(System.getenv(Constants.DATABASE_URL_ENV_VAR));

		String[] data = dbUri.getUserInfo().split(SEPARATOR);

		String username = data[0];
		String password = data[1];
		StringBuilder dbUrl = new StringBuilder(Constants.DB_URL_PREFIX);
		dbUrl.append(dbUri.getHost());
		dbUrl.append(SEPARATOR);
		dbUrl.append(dbUri.getPort());
		dbUrl.append(dbUri.getPath());

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(dbUrl.toString());
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);

		return basicDataSource;
	}

	/**
	 * This method builds the LocalContainerEntityManagerFactoryBean for
	 * hibernate configuration
	 * 
	 * @param dataSource
	 *            the dataSource
	 * @param env
	 *            the current Environment
	 * @return LocalContainerEntityManagerFactoryBean the
	 *         localContainerEntityManagerFactoryBean
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setDataSource(dataSource);
		lcemfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		lcemfb.setPackagesToScan(Constants.ENTITIES_PACKAGE);

		Properties jpaProperties = buildProperties(env);

		lcemfb.setJpaProperties(jpaProperties);

		return lcemfb;
	}

	/**
	 * This method creates JpaTransactionManager given the entityManagerFactory
	 * 
	 * @param entityManagerFactory
	 *            the entityManagerFactory
	 * @return JpaTransactionManager
	 */
	@Bean
	public JpaTransactionManager transactionManager(
			EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	/**
	 * Liquibase configuration
	 * 
	 * @param dataSource
	 *            the dataSource
	 * @return SpringLiquibase configuration
	 */
	@Bean
	public SpringLiquibase liquibase(DataSource dataSource) {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(dataSource);
		springLiquibase
				.setChangeLog("classpath:database/updates/db-changelog.xml");
		springLiquibase.setContexts("test, production");

		return springLiquibase;
	}

	/**
	 * This method builds JPA Properties
	 * 
	 * @param env
	 *            current Environment
	 * @return JPA Properties
	 */
	private Properties buildProperties(Environment env) {
		Properties jpaProperties = new Properties();

		// Configures the used database dialect. This allows Hibernate to create
		// SQL
		// that is optimized for the used database.
		jpaProperties.put(Constants.HIBERNATE_DIALECT_PROPERTY,
				env.getRequiredProperty(Constants.HIBERNATE_DIALECT_PROPERTY));

		// Specifies the action that is invoked to the database when the
		// Hibernate
		// SessionFactory is created or closed.
		jpaProperties
				.put(Constants.HIBERNATE_HBM2DDL_AUTO_PROPERTY,
						env.getRequiredProperty(Constants.HIBERNATE_HBM2DDL_AUTO_PROPERTY));

		// Configures the naming strategy that is used when Hibernate creates
		// new database objects and schema elements
		jpaProperties
				.put(Constants.HIBERNATE_EJB_NAMING_STRATEGY_PROPERTY,
						env.getRequiredProperty(Constants.HIBERNATE_EJB_NAMING_STRATEGY_PROPERTY));

		// If the value of this property is true, Hibernate writes all SQL
		// statements to the console.
		jpaProperties.put(Constants.HIBERNATE_SHOW_SQL_PROPERTY,
				env.getRequiredProperty(Constants.HIBERNATE_SHOW_SQL_PROPERTY));

		// If the value of this property is true, Hibernate will format the SQL
		// that is written to the console.
		jpaProperties.put(Constants.HIBERNATE_FORMAT_SQL_PROPERTY, env
				.getRequiredProperty(Constants.HIBERNATE_FORMAT_SQL_PROPERTY));
		
		return jpaProperties;
	}

}
