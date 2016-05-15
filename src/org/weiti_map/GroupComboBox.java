package org.weiti_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jws.soap.SOAPBinding;
import javax.swing.JComboBox;

public class GroupComboBox extends JComboBox<String> {

	private static final long serialVersionUID = 1L;

	// private String[] items;
	private MyPanel myPanel;

	GroupComboBox(MyPanel parent, String[] strings) {
		super(strings);
		// items = strings;
		getSelectedItem();
		myPanel = parent;
		configure();
	}

	private void configure() {
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myPanel.showGroupPlan(String.valueOf(getSelectedItem()));
			}
		});
	}

	@Override
	public String getSelectedItem() {
		String temp = (String) super.getSelectedItem();
		return temp;
	}
}
