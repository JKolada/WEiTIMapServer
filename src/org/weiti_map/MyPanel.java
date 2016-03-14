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
//		groupNameJTextField.setForeground(Color.GRAY);

		
		showGroupPlan();
//		insertJButton.setPreferredSize(new Dimension(50, 20));
//		insertJButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		
    }
    
    void refillPanel(SHOW_PANEL_TYPES type) {
    	removeAll();
		add(controlPanel, "wrap");
		
		switch (type) {
			case GROUP_TABLES:
				showPanel = new MyShowPanel(mDatabase, type);
				break;
			case ROOMS_TABLE:
				showPanel = new MyShowPanel(mDatabase, type);
				showWorkers();
				break;
			default:
				break;
		}
		add(showPanel, "wrap");
		revalidate();
		repaint();
    }
   
    
    void showGroupPlan(String groupName) {
    	refillPanel(SHOW_PANEL_TYPES.GROUP_TABLES);
		showPanel.setGroupPlanObject(mDatabase.getGroupPlanObject(groupName)); 
    }
    
    void showGroupPlan() {
    	refillPanel(SHOW_PANEL_TYPES.GROUP_TABLES);
		showPanel.setGroupPlanObject(mDatabase.getGroupPlanObject("1E1")); 
    }

	private void showWorkers() {
//		WorkersTableObject table = mDatabase.getWorkersTableObject();
//		showPanel.showWorkersTable(table);
		refillPanel(SHOW_PANEL_TYPES.WORKERS_TABLE);
	}


	void showRooms() {
//		RoomsTableObject table = mDatabase.getRoomsTableObject();
//		CustomTableObject<RoomObj> table = mDatabase.getRoomsTableObject();
//		showPanel.vshowRoomsTable(table);
		refillPanel(SHOW_PANEL_TYPES.ROOMS_TABLE);
	}        
	

	void insertRadioButtonClicked() {
		showPanel.resetTable();		
	}

	void showRadioButtonClicked() {
		showPanel.setVisible(true);
	}

}
