public class WEITImap {
	
	private static MyGUI mGUI; 
	private static MyDatabase mDB;
	
	public static void main(String[] args) {	
		
	    mDB = new MyDatabase();
	    mGUI = new MyGUI();
	    
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {
					MyGUI.createAndShowGUI();
				}}
		);
		
		
	}

}
