package org.weiti_map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sqlite.SQLiteDataSource;

public class MyDatabase extends SQLiteDataSource{
	
	private Boolean isSet = false;
	private Connection mConnection = null;
	private Statement mStatement = null;    
	
	
	public MyDatabase() {
	      try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	      
	      try {
			mConnection = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");	    
		    mStatement = mConnection.createStatement();
		      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	      resetDB();
	      checkTables(true);
	      setDatabase();
	      isSet = true; //TODO
	      
	}
	
	public Boolean isSet() {
		return isSet;
	}
	
	
	private void setDatabase() {
			
			int k = 0;
			for (String i: MyDatabaseUtilities.TABLE_CREATES_STATEMENTS) {
				
		    	try {
					mStatement.executeUpdate(i);
			    	System.out.println("Table " + MyDatabaseUtilities.TABLE_NAMES[k]+ " succesfully created or it exists." );	    
				} catch (SQLException e) {
					System.out.println("Table " + MyDatabaseUtilities.TABLE_NAMES[k] + " creation failed. StackTrace:" );		
					e.printStackTrace();
				}  	
		    	k++;
			}   
			
			try {
				mStatement.executeUpdate(MyDatabaseUtilities.CREATE_PLAN_VIEW);
		    	System.out.println("VW_PLAN succesfully created or it exists." );	    
			} catch (SQLException e) {
				System.out.println("VW_PLAN creation failed. StackTrace:" );		
				e.printStackTrace();
			}  	
			
			k = 0;
			for (String i: MyDatabaseUtilities.INSERT_INTO_STATEMENT_LIST) {
		    	try {
					mStatement.executeUpdate(i);
			    	System.out.println("\'" + MyDatabaseUtilities.INSERT_STATEMENT_NAMES[k]  + "\' insert statement succesfully executed." );	    
				} catch (SQLException e) {
					System.out.println("\'" + MyDatabaseUtilities.INSERT_STATEMENT_NAMES[k]  + "\' insert statement failed. StackTrace:" );	    
					e.printStackTrace();
				}  	
		    	k++;
		    	if (k >= MyDatabaseUtilities.INSERT_INTO_STATEMENT_LIST.length) {
		    		break;
		    	}
			}   
//			
			
			
	}
	

	private int checkTables (boolean reset) {
		return checkTables(reset, "ALL");
	}
	
	private int checkTables (boolean reset, String table_name_to_drop) {
		ResultSet result;
		int table_num = 0;
		String table_name_temp;		
		try {
			result = mStatement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name");
	        while(result.next()) {
	        	table_name_temp = result.getString("name");
	            System.out.println(table_name_temp + " exists.");
	            table_num++;
	            if (reset = true) {
	            	if ((table_name_to_drop.equals(table_name_temp)) || (table_name_to_drop.equals("ALL")))
					mStatement.execute("DROP TABLE " + table_name_temp);
					System.out.println(table_name_temp + " has been dropped.");
					table_num--;
	            }
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	        
	    if (table_num > 0) {
			System.out.println("Error: Some tables still exist." );	 
	    }
		return table_num;
		
			
	}
	
	private void resetDB() {		
		for(String i: MyDatabaseUtilities.TABLE_NAMES) {
			try {
				mStatement.execute("DROP TABLE " + i);
				System.out.println("Table \'" + i + "\' has been dropped succesfully");
			} catch (SQLException e) {
				System.out.println("Error: SQL Statement \'DROP TABLE " + i + "\' failed. StackTrace:");
				e.printStackTrace();
			}
		}
	}
	
	public GroupPlanObject getGroupPlanObject(String group_name) {
		String query = "SELECT * FROM vw_plan WHERE nazwa_grupy = '" + group_name +"'";
    	GroupPlanObject groupObject = null;
		try {
			groupObject = new GroupPlanObject(mStatement.executeQuery(query));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupObject;
	}
	
	public String[] getGroupNames() {
		String query = "SELECT nazwa_grupy FROM tb_grupy";			
		List<String> nazwy_grup = new ArrayList<String>();
		try {					
			ResultSet nazwy_grup_rs = mStatement.executeQuery(query);
		    while (nazwy_grup_rs.next() ) {	    	
		    	nazwy_grup.add(nazwy_grup_rs.getString("nazwa_grupy"));	    	  	    	
	    	}		    
	    } catch (SQLException e) {
    		e.printStackTrace();
    	}
		return nazwy_grup.toArray(new String[nazwy_grup.size()]);
	}
	
}





