import java.sql.*;

public class MyDatabase {
	
	public MyDatabase() {		
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      System.out.println("Opened database successfully");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }  
	    

	      try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	      
	      for (int i = 0; i < MyDatabaseUtilities.TABLE_CREATES_STATEMENTS.length; i++) {
		      try {
				stmt.executeUpdate(MyDatabaseUtilities.TABLE_CREATES_STATEMENTS[i]);
				System.out.println("Table " + MyDatabaseUtilities.TABLE_NAMES[i] + " succesfully created.\n" );
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Table " + MyDatabaseUtilities.TABLE_NAMES[i] + " creation failed.\n" );
			}
	      }	      

	      try {
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
