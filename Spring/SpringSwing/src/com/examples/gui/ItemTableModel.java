package com.examples.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author Rohtash Singh
 * @version 1.0
 * @since 05/19/2011
 */
public class ItemTableModel extends AbstractTableModel {

	//data
	private List<Object> itemList;

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	public String getColumnName(int column) {
		return "Items";
	}

	/**
	 * 
	 * @param itemList
	 */
	public void setItemList(List<Object> itemList) {
		this.itemList = itemList;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return itemList.size();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		itemList.set(rowIndex, value);
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		return itemList.get(rowIndex);
	}
}