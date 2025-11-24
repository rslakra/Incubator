package com.examples.gui.listeners;

import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * 
 * @author rosingh
 *
 */
public class ButtonActionListener extends JButton {
	
	private ActionListener actionListener;

	/**
	 * 
	 * @param actionListener
	 */
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	/**
	 * 
	 */
	public void init() {
		this.addActionListener(actionListener);
	}
}