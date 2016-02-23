package org.weiti_map;

import javax.swing.table.TableModel;

import org.weiti_map.GroupPlanObject;

public class CustomPlanTableModel extends PlanTableModel implements TableModel {
	
	private static final long serialVersionUID = 1L;

	CustomPlanTableModel(GroupPlanObject plan, char parzystosc) {
		super();
		plan.fillData(data, plan, parzystosc);	
	}	
}
