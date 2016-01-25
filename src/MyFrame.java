import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTable;



public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4065376415133611061L;

	private Container frameContainer;
	
	private JTable jtable;
	
	public MyFrame() {
		super("WEiTIstrator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
		setLocation(50,50);
		setVisible(true);	
		
	}
}
