package org.weiti_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import org.weiti_map.MyShowPanel.SHOW_PANEL_TYPES;

public class MainViewsComboBox extends JComboBox<String> {

	private static final long serialVersionUID = 1L;
	private GroupNameJPanel typeJPanel;
	private MyPanel myGrandPanel;
	private MyControlPanel myParentPanel;
	private JRadioButton showDataRadioButton;
	
	MainViewsComboBox(MyPanel myPanel, MyControlPanel myControlPanel, GroupNameJPanel tableTypeJPanel, String[] strings) {
		super(strings);
		typeJPanel = tableTypeJPanel;
		myGrandPanel = myPanel;
		myParentPanel = myControlPanel;
		showDataRadioButton = myControlPanel.getShowDataRadioButton();	
		configure();
	}
	

	private void configure() {

		addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = String.valueOf(getSelectedItem());
				switch (selectedItem) {
					case "Plan zajêæ":						
						myGrandPanel.showGroupPlan();		
						typeJPanel.restart();
						break;
					case "Zajêcia":
						typeJPanel.setVisible(false);
//						if (showDataRadioButton.isSelected()) {
//							myGrandPanel.refillPanel(SHOW_PANEL_TYPES.ROOMS_TABLE);
//						}
						break;
					case "Pracownicy":
						typeJPanel.setVisible(false);
//						if (showDataRadioButton.isSelected()) {
//							myGrandPanel.refillPanel(SHOW_PANEL_TYPES.WORKERS_TABLE);
//						}
						break;
					case "Sale":
						typeJPanel.setVisible(false);
						if (showDataRadioButton.isSelected()) {
							myGrandPanel.refillPanel(SHOW_PANEL_TYPES.ROOMS_TABLE);
						}
						break;
				}
			}
		});
	}
}
