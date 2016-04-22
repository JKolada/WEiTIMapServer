package org.weiti_map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.weiti_map.db.GroupPlanObject;
import org.weiti_map.db.MyDatabase;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

class PlanTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private char parzystosc;
	private PlanJTable planTable;
	private JLabel parzystoscLabel;
	private MyDatabase mDB;
	
	PlanTablePanel(MyDatabase mDatabase, char _parzystosc) {
		super();
		mDB = mDatabase;
		parzystosc = _parzystosc;		
		planTable = new PlanJTable(mDB);
		parzystoscLabel = new JLabel();
		configure();		
	}

	void setGroupPlan(GroupPlanObject plan) {
		 planTable.setGroupPlan(plan, parzystosc); 
	}	

	void resetTable() {
		planTable.resetTable();		
	}	
	
	private void configure() {
		if (parzystosc == 'P') {			
			parzystoscLabel.setText("Tydzieñ parzysty");
		} else {
			parzystoscLabel.setText("Tydzieñ nieparzysty");
		}
		planTable.getTableHeader().setReorderingAllowed(false);		
		LC layoutConstraints = new LC();
		layoutConstraints.setFillX(true);
		setLayout(new MigLayout(layoutConstraints));		
		add(parzystoscLabel, "wrap");		
		add(planTable.getTableHeader(), "wrap");
		add(planTable);		
	}

}
