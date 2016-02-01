package org.weiti_map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;

public class MyDatabase extends SQLiteDataSource{
	
	private Boolean isSet = false;
	private Connection mConnection = null;
//	private Statement mStatement = null;    
	private int db_errors_num = 0;
	
	
	public MyDatabase() {
	      try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	      
	      try {
			mConnection = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");	    
		      
		} catch (SQLException e) {
			db_errors_num++ ;
			e.printStackTrace();
			db_errors_num++ ;
		}

	      resetDB();
	      checkTables(true);
	      setDatabase();
	      isSet = true; //TODO
	      
	}
	
	public Boolean isSet() {
		return isSet;
	}
	
	
	private int setDatabase() {
			
			int k = 0;
			for (String i: MyDatabaseUtilities.TABLE_CREATES_STATEMENTS) {
				
		    	try {
		    		mConnection.createStatement().executeUpdate(i);
			    	System.out.println("Table " + MyDatabaseUtilities.TABLE_NAMES[k]+ " succesfully created or it exists." );	    
				} catch (SQLException e) {
					System.out.println("Table " + MyDatabaseUtilities.TABLE_NAMES[k] + " creation failed. StackTrace:" );		
					e.printStackTrace();
					db_errors_num++ ;
				}
		    	k++;
			}   
			
			try {
				mConnection.createStatement().executeUpdate(MyDatabaseUtilities.CREATE_PLAN_VIEW);
		    	System.out.println("VW_PLAN succesfully created or it exists." );
		    	
			} catch (SQLException e) {
				System.out.println("VW_PLAN creation failed. StackTrace:" );		
				e.printStackTrace();
				db_errors_num++ ;
			}  	
			
			k = 0;
			for (String i: MyDatabaseUtilities.INSERT_INTO_STATEMENT_LIST) {
		    	try {
		    		mConnection.createStatement().executeUpdate(i);
			    	System.out.println("\'" + MyDatabaseUtilities.INSERT_STATEMENT_NAMES[k]  + "\' insert statement succesfully executed." );	    
				} catch (SQLException e) {
					System.out.println("\'" + MyDatabaseUtilities.INSERT_STATEMENT_NAMES[k]  + "\' insert statement failed. StackTrace:" );	    
					e.printStackTrace();
					db_errors_num++ ;
				}  	
		    	k++;
		    	if (k >= MyDatabaseUtilities.INSERT_INTO_STATEMENT_LIST.length) {
		    		break;
		    	}
			}   
			if (db_errors_num == 0 ) {
				System.out.println("Database is set properly.\n");
			} else {
				System.out.println("Database has initialization failures. Number of SQLException caught: " + db_errors_num);
			}
			
			try {
				if (!mConnection.isClosed())
					return 1;
				else 
					return 0;
			} catch (SQLException e) {
				e.printStackTrace();
				db_errors_num++ ;
			}
			return 0;	
	}
	

	private int checkTables (boolean reset) {
		return checkTables(reset, "ALL");
	}
	
	private int checkTables (boolean reset, String table_name_to_drop) {
		ResultSet result;
		int table_num = 0;
		String table_name_temp;		
		try {
			result = mConnection.createStatement().executeQuery("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name");
	        while(result.next()) {
	        	table_name_temp = result.getString("name");
	            System.out.println(table_name_temp + " exists.");
	            table_num++;
	            if (reset = true) {
	            	if ((table_name_to_drop.equals(table_name_temp)) || (table_name_to_drop.equals("ALL")))
	            	mConnection.createStatement().execute("DROP TABLE " + table_name_temp);
					System.out.println(table_name_temp + " has been dropped.");
					table_num--;
	            }
	            result.close();
	        }
		} catch (SQLException e) {
			e.printStackTrace();
			db_errors_num++ ;
		}
	        
	    if (table_num > 0) {
			System.out.println("Error: Some tables still exist." );	 
	    }
		return table_num;
		
			
	}
	
	private void resetDB() {		
		for(String i: MyDatabaseUtilities.TABLE_NAMES) {
			try {
				mConnection.createStatement().execute("DROP TABLE " + i);
				System.out.println("Table '" + i + "' has been dropped succesfully");
			} catch (SQLException e) {
				System.out.println("Error: SQL Statement 'DROP TABLE " + i + "' failed. StackTrace:");
				e.printStackTrace();
				db_errors_num++ ;
			}
		}
		
		try {
			mConnection.createStatement().execute("DROP VIEW VW_PLAN");
			System.out.println("View VW_PLAN has been dropped succesfully");
		} catch (SQLException e) {
			System.out.println("Error: SQL Statement 'DROP VIEW VW_PLAN' failed. StackTrace:");
			e.printStackTrace();
			db_errors_num++ ;
		}
		
		
	}
	
	public GroupPlanObject getGroupPlanObject(String group_name) {
		String query = "SELECT * FROM vw_plan WHERE nazwa_grupy = '" + group_name +"'";
    	GroupPlanObject groupObject = new GroupPlanObject();
//		List<String> pojedyncze_zajecia = new ArrayList<String>();		
		try {
	    	ResultSet zajeciaRS = mConnection.createStatement().executeQuery(query);
			groupObject = new GroupPlanObject(group_name);
			if (!zajeciaRS.isClosed()) {
			    while (zajeciaRS.next()) {	 
			    	List<String> pojedyncze_zajecia = new ArrayList<String>();	
		    		for (int k = 2; k <= MyDatabaseUtilities.PLAN_VIEW_COL_NAMES.length; k++) {
		    			pojedyncze_zajecia.add(zajeciaRS.getString(k));
//		    			System.out.println(zajeciaRS.getString(k));  //TO DELETE
		    		}
		    		groupObject.add(new MyLecture((ArrayList<String>) pojedyncze_zajecia));		    	  	    	
		    	} 
		    		
			} else {
	    		System.out.println("Result set is null"); //TO DELETE
	    	}
		    zajeciaRS.close();
	    } catch (SQLException e) {
    		e.printStackTrace();
    	}
	    return groupObject;
	}
	
	public void temp() {
		try {
			
			ResultSet zajeciaRS = mConnection.createStatement().executeQuery(
					 "SELECT * FROM vw_plan WHERE nazwa_grupy = '1E1'");
			
			if (!zajeciaRS.isClosed()) {
				while(zajeciaRS.next()) {
//					for (int k = 1; k <= 2; k++) {
						System.out.print(zajeciaRS.getString(1) + ' ');
						System.out.print(zajeciaRS.getString(2) + ' ');
						System.out.print(zajeciaRS.getString(3) + ' ');//
						System.out.print(zajeciaRS.getString(4) + ' ');//
						System.out.print(zajeciaRS.getString(5) + ' ');//
						System.out.println(zajeciaRS.getString(6));
//					}
				}
			} else {
				System.out.println("dupa"); //TO DELETE
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("buu");
		}
		
	}
	
	
	public String[] getGroupNames() {
		String query = "SELECT nazwa_grupy FROM tb_grupy";			
		List<String> nazwy_grup = new ArrayList<String>();
		try {					
			ResultSet nazwy_grup_rs = mConnection.createStatement().executeQuery(query);
		    while (nazwy_grup_rs.next() ) {	    	
		    	nazwy_grup.add(nazwy_grup_rs.getString("nazwa_grupy"));	    	  	    	
		    }
		    nazwy_grup_rs.close();
	    } catch (SQLException e) {
    		e.printStackTrace();
    	}
		return nazwy_grup.toArray(new String[nazwy_grup.size()]);
	}
	
}





