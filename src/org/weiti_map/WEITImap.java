package org.weiti_map;

public class WEITImap {
	
	@SuppressWarnings("unused")
	private static MyGUI mGUI; 
	
	public static void main(String[] args) {			
	    mGUI = new MyGUI(new MyDatabase());			    
		javax.swing.SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					MyGUI.createAndShowGUI();
				}
			}
		);				
	}
}
