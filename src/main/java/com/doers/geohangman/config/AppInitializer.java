package com.doers.geohangman.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
@Configuration
@ComponentScan("com.doers.geohangman")
public class AppInitializer implements WebApplicationInitializer {
	
	/** Load on startup constant **/
	private static final int LOAD_ON_STARTUP = 1;

	/** Mapping using for servlet **/
	private static final String MAPPING = "/";
	
	/** Dispatcher servlet name **/
	private static final String DISPATCHER_NAME = "dispatcher";

	/* (non-Javadoc)
	 * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
	 */
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppInitializer.class);
		context.setServletContext(servletContext);
		
		Dynamic servlet = servletContext.addServlet(DISPATCHER_NAME, new DispatcherServlet(context));
		servlet.addMapping(MAPPING);
		servlet.setLoadOnStartup(LOAD_ON_STARTUP);
	}
	
}
