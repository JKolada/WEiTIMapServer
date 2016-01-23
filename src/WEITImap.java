public class WEITImap {
	
	private static MyGUI mGUI; 
	private static MyDatabase mDB;
	
	public static void main(String[] args) {	
		
	    try {
			mDB = new MyDatabase();
		    mGUI = new MyGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		javax.swing.SwingUtilities.invokeLater(
				new Runnable() { public void run() {
					MyGUI.createAndShowGUI();
				}}
		);
		
		
	}

}
