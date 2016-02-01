package org.weiti_map;

import javax.swing.table.TableModel;

public class CustomPlanTableModel extends DefaultPlanTableModel	implements TableModel {
	
	private static final long serialVersionUID = 1L;

	public CustomPlanTableModel(GroupPlanObject plan, char parzystosc) {
		super();
		plan.fillData(data, plan, parzystosc);	
	}
}
