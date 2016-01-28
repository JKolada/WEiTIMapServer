package org.weiti_map;

import javax.swing.table.TableModel;

public class CustomPlanTableModel extends DefaultPlanTableModel	implements TableModel {

	
	public CustomPlanTableModel(GroupPlanObject plan, char parzystosc) {
		super();
//		setData(plan, parzystosc);
//		for (int row_no = 0; row_no < 12; row_no++) {	
			plan.modifyData(data, plan, parzystosc);			
//		}
	}
//	
//	private setData() {
//		
//	}


}
