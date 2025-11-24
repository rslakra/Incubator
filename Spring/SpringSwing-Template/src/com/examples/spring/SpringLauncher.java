package com.examples.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * The purpose of the Launcher class is to initialize and launch the Spring
 * framework by creating an ApplicationContext and passing it an array
 * containing the paths to the bean definition file(s) that you'll create in
 * Creating the Spring app-context.xml bean definition file. Spring creates the
 * MainFrame class automatically when the framework starts up, because the bean
 * will be defined as a Singleton (see Resources). There are several other types
 * of ApplicationContext implementations besides ClassPathXmlApplicationContext,
 * but they all serve as a way to provide configuration for a Spring
 * application.
 * 
 * @author Rohtash Singh
 * @version 1.0
 * @since 05/19/2011
 */
public class SpringLauncher {
	/**
	 * This
	 */
	public void launch() {
		String[] contextPaths = new String[] { "conf/app-context.xml" };
		new ClassPathXmlApplicationContext(contextPaths);
	}
}