package com.examples.gui.listeners;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;

/**
 * 
 * @author rosingh
 *
 */
public abstract class ListTableActionListener implements ActionListener {
	
	protected JTable table;
	protected List list;

	/**
	 * 
	 * @param list
	 */
	public void setList(List list) {
		this.list = list;
	}

	/**
	 * 
	 * @param itemTable
	 */
	public void setTable(JTable itemTable) {
		this.table = itemTable;
	}
}