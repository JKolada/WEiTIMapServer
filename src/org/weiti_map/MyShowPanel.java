package org.weiti_map;

import javax.swing.JPanel;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MyShowPanel extends JPanel {	

	private static final long serialVersionUID = 1L;	
			
	public enum SHOW_PANEL_TYPES {GROUP_TABLES, WORKERS_TABLE, ROOM_TABLE, LECTURES_TABLE};
	private SHOW_PANEL_TYPES panel_type;
	
	private MyDatabase mDatabase;
	private MyTablePanel tablePanelP;
	private MyTablePanel tablePanelN;
	
	   MyShowPanel(MyDatabase mDB, SHOW_PANEL_TYPES type) {
			super();			
			mDatabase = mDB;
			panel_type = type;
			tablePanelP = new MyTablePanel('P');
			tablePanelN = new MyTablePanel('N');			

			LC layoutConstraints = new LC();
			layoutConstraints.setFillX(true);
			setLayout(new MigLayout(layoutConstraints));
			
			add(tablePanelP);
			add(tablePanelN);
//			add(insertJButton, "wrap");
						
	   }	   
	   	   

	    void showGroupPlan(GroupPlanObject plan) {
	    	tablePanelP.setGroupPlan(plan);
	    	tablePanelN.setGroupPlan(plan);    	   	    	
	    }

		void resetTable() {
			tablePanelP.resetTable();
	    	tablePanelN.resetTable();  			
		}
	    
		SHOW_PANEL_TYPES getPanelType() {
			return panel_type; 
		}
}
