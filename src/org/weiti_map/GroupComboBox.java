package org.weiti_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class GroupComboBox extends JComboBox<Object> {

	private static final long serialVersionUID = 1L;
	
	private MyPanel myPanel;
	
	GroupComboBox(MyPanel parent, String[] strings) {
		super(strings);
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
	
	
	
}
