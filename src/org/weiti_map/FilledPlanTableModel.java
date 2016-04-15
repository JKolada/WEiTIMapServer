package org.weiti_map;

import org.weiti_map.db.GroupPlanObject;

public class FilledPlanTableModel extends PlanTableModel {
	
	private static final long serialVersionUID = 1L;

	FilledPlanTableModel(GroupPlanObject plan, char parzystosc) {
		super();
		plan.fillData(data, plan, parzystosc);
	}	
}
