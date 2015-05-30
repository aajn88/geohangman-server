package com.doers.geohangman.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@SpringBootApplication
@ComponentScan("com.doers.geohangman")
@PropertySource("geohangman.properties")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
