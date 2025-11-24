package com.examples.gui.listeners;

import java.awt.event.ActionEvent;

/**
 * 
 * @author rosingh
 * 
 */
public class NewButtonActionListener extends ListTableActionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		list.add("New Item");
		table.revalidate();
	}
}