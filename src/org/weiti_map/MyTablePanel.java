package org.weiti_map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

class MyTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private char parzystosc;
	private MyJTable planTable;
	private JLabel parzystoscLabel;
	
	MyTablePanel(char _parzystosc) {
		super();
		parzystosc = _parzystosc;		
		planTable = new MyJTable();
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
