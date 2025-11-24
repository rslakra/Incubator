package com.examples.gui;

import java.awt.Component;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * 
 * @author Rohtash Singh
 * @version 1.0
 * @since 05/19/2011
 */
public class BoxLayoutPanel extends JPanel {
	/**
	 * We can't use "components" as the property name, because it conflicts with
	 * an existing property on the Component superclass.
	 */
	private List<Component> panelComponents;
	private int axis;

	public void setAxis(int axis) {
		this.axis = axis;
	}

	public void setPanelComponents(List<Component> panelComponents) {
		this.panelComponents = panelComponents;
	}

	public void init() {
		setLayout(new BoxLayout(this, axis));

		for (Component component : panelComponents) {
			add(component);
		}

		// for (Iterator<Component> iter = panelComponents.iterator(); iter
		// .hasNext();) {
		// Component component = iter.next();
		// add(component);
		// }
	}
}
