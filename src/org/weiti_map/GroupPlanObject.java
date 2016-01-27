package org.weiti_map;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupPlanObject {
	private String nazwa_grupy;	
	private List<MyLecture> zajecia = new ArrayList<MyLecture>();
	
	
	public GroupPlanObject(ResultSet zajeciaRS) {
		
		List<String> pojedyncze_zajecia = new ArrayList<String>();
		try {
			nazwa_grupy = zajeciaRS.getString("nazwa_grupy");
		    while (zajeciaRS.next() ) {	    	
		    	int k = 0;
	    		for (String nazwa_kolumny: MyDatabaseUtilities.PLAN_VIEW_COL_NAMES) {
	    			pojedyncze_zajecia.set(k, zajeciaRS.getString(nazwa_kolumny));
	    			k++;
	    		}
	    			zajecia.add(new MyLecture((ArrayList<String>) pojedyncze_zajecia));		    	  	    	
	    	}
	    } catch (SQLException e) {
    		e.printStackTrace();
    	}
	     
	}
	
	public String getGroupName() {
		return nazwa_grupy;
	}
	
	

}
