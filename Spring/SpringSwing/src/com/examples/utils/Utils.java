package com.examples.utils;

public class Utils {

	private static Utils instance;

	/**
	 * 
	 */
	private Utils() {

	}

	/**
	 * 
	 * @return
	 */
	public static Utils getInstance() {
		if (instance == null) {
			instance = new Utils();
		}
		return null;
	}

	/**
	 * Returns the user's current directory.
	 * 
	 * @return
	 */
	public String getUserDir() {
		return System.getProperty("user.dir");
	}
}