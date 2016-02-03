package org.weiti_map;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTable;



public class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4065376415133611061L;
	private MyPanel myContentPane;
	
	public MyFrame(MyDatabase mDB) {
		super("WEiTIstrator");		
		add(new MyPanel(mDB));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(450,500); //TODO
		setMinimumSize(new Dimension(1000, 600));
        pack();
		setVisible(true);	        
		
	}
}
