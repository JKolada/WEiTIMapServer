import java.awt.Cursor;
import java.io.*;
import java.sql.*;

import org.sqlite.core.DB;

public class MyDatabase {
	
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
//	      checkTables();
	      setDatabase();
	      
	}
	
	
	private void setDatabase() {

			for (int i = 0; i < MyDatabaseUtilities.TABLE_CREATES_STATEMENTS.length; i++) {
		    	try {
					mStatement.executeUpdate(MyDatabaseUtilities.TABLE_CREATES_STATEMENTS[i]);
			    	System.out.println("Table " + MyDatabaseUtilities.TABLE_NAMES[i] + " succesfully created or it exists." );	    
				} catch (SQLException e) {
					System.out.println("Table " + MyDatabaseUtilities.TABLE_NAMES[i] + " creation failed.\n" );		
					e.printStackTrace();
				}  	
			}        
			try {
				mStatement.executeUpdate(MyDatabaseUtilities.TB_DNI_TYG_INSERTS);
				mStatement.executeUpdate(MyDatabaseUtilities.TB_GODZINY_INSERTS);  		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
//				mStatement.executeUpdate(MyDatabaseUtilities.TB_GRUPY_TEST_INSERT); 

//				mStatement.executeUpdate(MyDatabaseUtilities.TB_SALE_TEST_INSERTS);

				mStatement.executeUpdate(MyDatabaseUtilities.TB_PLAN_TEST_INSERT);
				
		    	System.out.println("TB_GRUPY_TEST_INSERT succesfully executed." );	 
				mStatement.close();
				mConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
	}
	
	private void checkTables () {
		ResultSet result;
		try {
			result = mStatement.executeQuery("SELECT name FROM sqlite_master WHERE type='name'");
			String id;
	        while(result.next()) {
	        	id = result.getString("table_name");
	            System.out.println(id);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
