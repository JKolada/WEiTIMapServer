package org.weiti_map;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MyPanel extends JPanel {

	private static final long serialVersionUID = 5169492090332131771L;
	private MyDatabase mDatabase;

	private MyControlPanel controlPanel;
	private MyShowPanel showPanel;
	
//	private JTextArea groupNameJTextArea = new JTextArea("Nazwa grupy:");
//	private JTextField groupNameJTextField = new JTextField("wprowadü nazwÍ");
//	private JButton insertJButton = new JButton("Wprowadü dane");

//	private JTextField logJTextField = new JTextField("Log programu");
	
    MyPanel(MyDatabase mDB) {
		super();
		mDatabase = mDB;
		controlPanel = new MyControlPanel(this, mDatabase);
		showPanel = new MyShowPanel(mDatabase);

		LC layoutConstraints = new LC();
		layoutConstraints.setFillX(true);
		setLayout(new MigLayout(layoutConstraints));
//    	setOpaque(true);
		
//		groupNameJTextArea.setOpaque(true);
//		groupNameJTextArea.setEditable(false);
//		groupNameJTextField.setForeground(Color.GRAY);
//		logJTextField.setEditable(false);
		
		showGroupPlan("1E1");
//		mDB.temp();			
		
//		insertJButton.addActionListener(this);
//		insertJButton.setPreferredSize(new Dimension(50, 20));
//		insertJButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(controlPanel, "wrap");		
		add(showPanel, "wrap");
//		add(insertJButton, "wrap");
//		add(logJTextField);
		
		
    }    
   
    void showLectures() {

    	
    }
    
    void showGroupPlan(String groupName) {
    	GroupPlanObject plan = mDatabase.getGroupPlanObject(groupName);
    	showPanel.showGroupPlan(plan);  	    	
    }


	void showWorkers() {
		WorkersTableObject table = mDatabase.getWorkersTableObject();
	}


	void showRooms() {
		// TODO Auto-generated method stub		
	}        
	

	void insertRadioButtonClicked() {
		showPanel.resetTable();		
	}

	void showRadioButtonClicked() {
		showPanel.setVisible(true);
	}

}
