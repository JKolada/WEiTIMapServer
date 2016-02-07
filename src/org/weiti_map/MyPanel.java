package org.weiti_map;

import javax.swing.JPanel;

import org.weiti_map.MyShowPanel.SHOW_PANEL_TYPES;

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
		showPanel = new MyShowPanel(mDatabase, SHOW_PANEL_TYPES.GROUP_TABLES);

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
		
		refillPanel();
		
//		insertJButton.addActionListener(this);
//		insertJButton.setPreferredSize(new Dimension(50, 20));
//		insertJButton.setHorizontalAlignment(SwingConstants.CENTER);
		
//		add(insertJButton, "wrap");
//		add(logJTextField);
		
		
    }
    
    void refillPanel() {
    	removeAll();
		add(controlPanel, "wrap");	
		showPanel = new MyShowPanel(mDatabase, SHOW_PANEL_TYPES.GROUP_TABLES);
		add(showPanel, "wrap");
		revalidate();
		repaint();
    }
   
    void showLectures() {

    	
    }
    
    void showGroupPlan(String groupName) {
    	GroupPlanObject plan = mDatabase.getGroupPlanObject(groupName);
    	showPanel.showGroupPlan(plan);    	
    }


	void showWorkers() {
//		WorkersTableObject table = mDatabase.getWorkersTableObject();
		System.out.println("lol");
		refillPanel();
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
