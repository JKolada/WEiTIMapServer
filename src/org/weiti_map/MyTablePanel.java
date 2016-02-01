package org.weiti_map;

import java.awt.image.ReplicateScaleFilter;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MyTablePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyJTable planTable = new MyJTable();
	private char parzystosc;
	private JLabel parzystoscLabel = new JLabel();
	
	public MyTablePanel(char _parzystosc) {
		super();
		parzystosc = _parzystosc;		
		setLabel();
		planTable.getTableHeader().setReorderingAllowed(false);		
		LC layoutConstraints = new LC();
		layoutConstraints.setFillX(true);
		setLayout(new MigLayout(layoutConstraints));		
		add(parzystoscLabel, "wrap");		
		add(planTable.getTableHeader(), "wrap");
		add(planTable);		
	}

	public void setGroupPlan(GroupPlanObject plan) {
		 planTable.setGroupPlan(plan, parzystosc);		 
	}
	
	private void setLabel() {
		if (parzystosc == 'P') {			
			parzystoscLabel.setText("Tydzieñ parzysty");
		} else {
			parzystoscLabel.setText("Tydzieñ nieparzysty");
		}		
	}
	
}
