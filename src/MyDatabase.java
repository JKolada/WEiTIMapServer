import java.awt.Cursor;
import java.io.*;
import java.sql.*;

import org.sqlite.SQLiteDataSource;
import org.sqlite.core.DB;

public class MyDatabase extends SQLiteDataSource{
	
	Connection mConnection = null;
    Statement mStatement = null;
    
	
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

	      checkTables();
		  resetDB();
	      checkTables();
	      setDatabase();
	      
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
				mStatement.executeUpdate(MyDatabaseUtilities.TB_DNI_TYG_INSERTS);
				mStatement.executeUpdate(MyDatabaseUtilities.TB_GODZINY_INSERTS);  		
			} catch (SQLException e) {
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
			
			
			
	}
	
	private int checkTables () {
		ResultSet result;
		int table_num = 0;
		String table_name_temp;		
		try {
			result = mStatement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' ORDER BY name");
	        while(result.next()) {
	        	table_name_temp = result.getString("name");
	            System.out.println(table_name_temp + " exists.");
	            table_num++;
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

//		try {
//			mStatement.execute("DROP TABLE tb_rodz_zajec");
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
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
	

}
