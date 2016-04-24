package org.weiti_map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;

public class MyDatabase extends SQLiteDataSource{
	
	private Boolean isSet = false;
	private Connection mConnection = null;
	private int db_errors_num = 0;
	
	
	 MyDatabase() {
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
	      if (db_errors_num == 0) {
	    	  isSet = true; //TODO
	      }	      
	      
	}
	
	Boolean isSet() {
		return isSet;
	}
	
	
	private int setDatabase() {
			
			int k = 0;
			for (String i: MyDatabaseUtilities.CREATE_TABLE_STATEMENTS) {
				
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
			
			k = 0;
			for (String i: MyDatabaseUtilities.CREATE_VIEW_STATEMENTS) {
				
		    	try {
		    		mConnection.createStatement().executeUpdate(i);
			    	System.out.println("View " + MyDatabaseUtilities.VIEW_NAMES[k]+ " succesfully created or it exists." );	    
				} catch (SQLException e) {
					System.out.println("View " + MyDatabaseUtilities.VIEW_NAMES[k] + " creation failed. StackTrace:" );		
					e.printStackTrace();
					db_errors_num++ ;
				}
		    	k++;
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
				System.out.println("Error: SQL Statement 'DROP TABLE '" + i + "' failed. StackTrace:");
				e.printStackTrace();
				db_errors_num++ ;
			}
		}
		
		for(String i: MyDatabaseUtilities.VIEW_NAMES) {
			try {
				mConnection.createStatement().execute("DROP VIEW " + i);
				System.out.println("View '" + i + "' has been dropped succesfully");
			} catch (SQLException e) {
				System.out.println("Error: SQL Statement 'DROP VIEW '" + i + "' failed. StackTrace:");
				e.printStackTrace();
				db_errors_num++ ;
			}
		}
		
		
	}

	String[] getGroupNames() {
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
	
	GroupPlanObject getGroupPlanObject(String group_name) {
		String query = "SELECT * FROM vw_plan WHERE nazwa_grupy = '" + group_name +"'";
		GroupPlanObject groupObject = null;
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
		    		groupObject.add(new LectureObj((ArrayList<String>) pojedyncze_zajecia));		    	  	    	
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


	 WorkersTableObject getWorkersTableObject() {
		String query = "SELECT * FROM tb_pracownicy ORDER BY 1";
    	WorkersTableObject workersTable = null;	
		try {
	    	ResultSet pracownicyRS = mConnection.createStatement().executeQuery(query);
			workersTable = new WorkersTableObject();
			if (!pracownicyRS.isClosed()) {
			    while (pracownicyRS.next()) {	 
			    	List<String> pojedynczy_pracownik = new ArrayList<String>();	
		    		for (int k = 0; k < 3; k++) {
		    			pojedynczy_pracownik.add(pracownicyRS.getString(k+1));
//		    			System.out.println(pracownicyRS.getString(k+1));  //TO DELETE
		    		}
		    		workersTable.add(new WorkerObj((ArrayList<String>) pojedynczy_pracownik));		    	  	    	
		    	} 
		    		
			} else {
	    		System.out.println("Result set is null"); //TO DELETE
	    	}
		    pracownicyRS.close();
	    } catch (SQLException e) {
    		e.printStackTrace();
    	}
	    return workersTable;
	 }

	RoomsTableObject getRoomsTableObject() {
		String query = "SELECT * FROM tb_sale ORDER BY 1";
    	RoomsTableObject roomsTable = null;	
		try {
	    	ResultSet saleRS = mConnection.createStatement().executeQuery(query);
			roomsTable = new RoomsTableObject();
			if (!saleRS.isClosed()) {
			    while (saleRS.next()) {	 
			    	List<String> pojedyncza_sala = new ArrayList<String>();	
		    		for (int k = 0; k < 5; k++) {
		    			pojedyncza_sala.add(saleRS.getString(k+1));
//		    			System.out.println(saleRS.getString(k+1));  //TO DELETE
		    		}
		    		roomsTable.add(new RoomObj((ArrayList<String>) pojedyncza_sala));		    	  	    	
		    	} 
		    		
			} else {
	    		System.out.println("Result set is null"); //TO DELETE
	    	}
		    saleRS.close();
	    } catch (SQLException e) {
    		e.printStackTrace();
    	}
	    return roomsTable;
	}	

//	LecturesTableObject getLectureTableObject() { //TODO 
//		String query = "SELECT * FROM VW_LECTURES ORDER BY 1";
//		LecturesTableObject lecturesTable = null;	
//		try {
//	    	ResultSet lecturesRS = mConnection.createStatement().executeQuery(query);
//			lecturesTable = new LecturesTableObject();
//			if (!lecturesRS.isClosed()) {
//			    while (lecturesRS.next()) {	 
//			    	List<String> pojedyncze_zajecia = new ArrayList<String>();	
//		    		for (int k = 0; k < 6; k++) {
//		    			pojedyncze_zajecia.add(lecturesRS.getString(k+1));
////		    			System.out.println(lecturesRS.getString(k+1));  //TO DELETE
//		    		}
//		    		lecturesTable.add(new RoomObj((ArrayList<String>) pojedyncze_zajecia );		    	  	    	
//		    	} 
//		    		
//			} else {
//	    		System.out.println("Result set is null"); //TO DELETE
//	    	}
//		    lecturesRS.close();
//	    } catch (SQLException e) {
//    		e.printStackTrace();
//    	}
//	    return lecturesTable;
//	}
	
//	KonsulTableObject getKonsulTableObject() { //TODO 
//		String query = "SELECT * FROM VW_KONSUL ORDER BY 1";
//		KonsulTableObject konsulTable = null;	
//		try {
//	    	ResultSet konsulRS = mConnection.createStatement().executeQuery(query);
//			konsulTable = new KonsulTableObject();
//			if (!konsulRS.isClosed()) {
//			    while (konsulRS.next()) {	 
//			    	List<String> pojedyncze_konsul = new ArrayList<String>();	
//		    		for (int k = 0; k < 6; k++) {
//		    			pojedyncze_konsul.add(konsulRS.getString(k+1));
////		    			System.out.println(konsulRS.getString(k+1));  //TO DELETE
//		    		}
//		    		konsulTable.add(new KonsulObj((ArrayList<String>) pojedyncze_konsul );		    	  	    	
//		    	} 
//		    		
//			} else {
//	    		System.out.println("Result set is null"); //TO DELETE
//	    	}
//		    konsulRS.close();
//	    } catch (SQLException e) {
//   		e.printStackTrace();
//   	}
//	    return konsulTable;
//	}
	
}





