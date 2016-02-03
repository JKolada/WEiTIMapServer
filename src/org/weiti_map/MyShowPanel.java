package org.weiti_map;

import javax.swing.JPanel;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MyShowPanel extends JPanel {	

	private static final long serialVersionUID = 1L;
	
	private MyDatabase mDatabase;
	private MyTablePanel tablePanelP;
	private MyTablePanel tablePanelN;
	
	   public MyShowPanel(MyDatabase mDB) {
			super();			
			mDatabase = mDB;
			tablePanelP = new MyTablePanel('P');
			tablePanelN = new MyTablePanel('N');			

			LC layoutConstraints = new LC();
			layoutConstraints.setFillX(true);
			setLayout(new MigLayout(layoutConstraints));
			
			add(tablePanelP);
			add(tablePanelN);
//			add(insertJButton, "wrap");
						
	   }	   
	   

	    public void showGroupPlan(GroupPlanObject plan) {
	    	tablePanelP.setGroupPlan(plan);
	    	tablePanelN.setGroupPlan(plan);    	   	    	
	    }

		public void resetTable() {
			tablePanelP.resetTable();
	    	tablePanelN.resetTable();  			
		}
	    
	
}
