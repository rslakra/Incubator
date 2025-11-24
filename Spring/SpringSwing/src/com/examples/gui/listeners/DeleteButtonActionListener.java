package com.examples.gui.listeners;

import java.awt.event.ActionEvent;

/**
 * 
 * @author rosingh
 * 
 */
public class DeleteButtonActionListener extends ListTableActionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();

		if (selectedRow == -1) {
			// if there is no selected row, don't do anything
			return;
		}

		if (table.isEditing()) {
			// if we are editing the table, don't do anything
			return;
		}

		list.remove(selectedRow);
		table.revalidate();
	}
}