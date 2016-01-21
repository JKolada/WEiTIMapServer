import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class MyFrame extends JFrame {

	private Container frameContainer;
	
	private JTable jtable;
	
	public MyFrame() {
		super("WEiTIstrator");
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
		setLocation(50,50);
		setVisible(true);	
		
		
		
		final JPanel jPanel = new JPanel();
		add(jPanel);		
		
		jPanel.add(new MyJTable());			
		
	}
}
