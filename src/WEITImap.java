import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class WEITImap {

	private static void createAndShowGUI() {
			
        JFrame frame = new JFrame("WEiTIstrator");
		frame.setMinimumSize(new Dimension(800, 500));
        MyPanel newContentPane = new MyPanel();      
      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);  
                
        frame.add(new JTextField());
        
        
        
        frame.pack();
        frame.setVisible(true);
// 
//        //Create the menu bar.  Make it have a green background.
//        JMenuBar greenMenuBar = new JMenuBar();
//        greenMenuBar.setOpaque(true);
//        greenMenuBar.setBackground(new Color(154, 165, 127));
//        greenMenuBar.setPreferredSize(new Dimension(200, 20));
//        
//        JMenuBar jMenu = new JMenuBar();
////        jMenu.
//        greenMenuBar.add(jMenu);
// 
//        //Create a yellow label to put in the content pane.
//        JLabel yellowLabel = new JLabel();
//        yellowLabel.setOpaque(true);
//        yellowLabel.setBackground(new Color(248, 213, 131));
//        yellowLabel.setPreferredSize(new Dimension(200, 180));
// 
//        //Set the menu bar and add the label to the content pane.
//        frame.setJMenuBar(greenMenuBar);
//        frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);
// 
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//        
//        JPanel contentPane = new JPanel(new BorderLayout());
//        contentPane.setBorder(someBorder);
//        contentPane.add(someComponent, BorderLayout.CENTER);
//        contentPane.add(anotherComponent, BorderLayout.PAGE_END);
        
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      System.out.println("Opened database successfully");

//	      stmt = c.createStatement();
//	      String sql = "drop TABLE COMPANY "; // +
//	                   "(ID INT PRIMARY KEY     NOT NULL," +
//	                   " NAME           TEXT    NOT NULL, " + 
//	                   " AGE            INT     NOT NULL, " + 
//	                   " ADDRESS        CHAR(50), " + 
//	                   " SALARY         REAL)"; 
//	      stmt.executeUpdate(sql);
//	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");
			
			
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
		
		
	}

}
