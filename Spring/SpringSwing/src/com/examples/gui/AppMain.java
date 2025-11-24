package com.examples.gui;

import org.apache.log4j.xml.DOMConfigurator;

import com.examples.spring.SpringLauncher;

/**
 * The main class of application.
 * 
 * @author Rohtash Singh
 * @version 1.0
 * @since 05/19/2011
 */
public class AppMain {

	/**
	 * <p>
	 * Configure Logger.
	 * </p>
	 */
	static {
		DOMConfigurator.configure(System.getProperty("user.dir") + "/conf/springSwing-log4j.xml");
	}

	/**
	 * This method simply creates a Launcher and calls launch() on it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringLauncher springLauncher = new SpringLauncher();
		springLauncher.launch();
	}
}