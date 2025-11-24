package com.examples.gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * 
 * @author Rohtash Singh
 * @version 1.0
 * @since 05/19/2011
 */
public class MainFrame extends JFrame {

	/**
	 * This method will be the first method in the application invoked by Spring
	 * and the entry point to the Swing application
	 */
	public void init() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(new Dimension(400, 300));

		// setState(Frame.NORMAL);
		setVisible(true);
	}
}