package org.weiti_map.table_models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.weiti_map.db.GroupPlanObject;
import org.weiti_map.db.LectureObj;
import org.weiti_map.db.MyDatabase;

public class FilledPlanTableModel extends PlanTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private GroupPlanObject planObject;

	private MyDatabase mDB;
	
	private char parzystosc;
	
	public FilledPlanTableModel(MyDatabase myDatabase, GroupPlanObject plan, char parzystos) {
		super();
		mDB = myDatabase;
		parzystosc = parzystos;
		planObject = plan;
		plan.fillData(data, plan, parzystosc);
	}		
	
    @Override
    public boolean isCellEditable(int row, int column) {
    	if (column == 0) {
	       return false;
    	}
    	return true;
    }
	
	public void setValueAt(Object value, int row, int col) {

		String string = (String) value;	
		String old_string = (String) super.getValueAt(row,col);
		
		System.out.println("string = " + string);
		System.out.println("old_string = " + old_string);	
		
		Boolean to_update = !(string.equals(old_string) || (string.isEmpty() && old_string == null));
		
		if (to_update) {
			Pattern p = Pattern.compile("([A-Z]+)[ ]([WLCR])[ ]([0-9A-Z-]+)");
			Matcher m = p.matcher(string);
			boolean b = m.matches() || string.isEmpty();
			 
	        if (b) {
	        	data[row][col] = string;      
	            fireTableCellUpdated(row, col);
	            if (string.isEmpty()) {
		            mDB.updatePlanCell(row, col, planObject, parzystosc, null);     
		            System.out.println("null");
	            } else {
		            mDB.updatePlanCell(row, col, planObject, parzystosc, string);
		            System.out.println("nie null");
	            }	            
	        } else System.out.println("Ni pasuji!");
	        
		}
		
    }
}
