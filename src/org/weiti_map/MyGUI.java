package org.weiti_map;

import javax.swing.JFrame;

import org.weiti_map.db.MyDatabase;

public class MyGUI {

	private static MyDatabase mDB;
	// private static MyPanel newContentPane;

	@SuppressWarnings("unused")
	private static JFrame frame;

	MyGUI(MyDatabase DB) {
		mDB = DB;
	}

	static void createAndShowGUI() {
		// newContentPane = new MyPanel(mDB);
		frame = new MyFrame(mDB);
		// frame.add(new JTextField());
		//
		// //Create the menu bar. Make it have a green background.
		// JMenuBar greenMenuBar = new JMenuBar();
		// greenMenuBar.setOpaque(true);
		// greenMenuBar.setBackground(new Color(154, 165, 127));
		// greenMenuBar.setPreferredSize(new Dimension(200, 20));
		//
		// JMenuBar jMenu = new JMenuBar();
		//// jMenu.
		// greenMenuBar.add(jMenu);
		//
		// //Create a yellow label to put in the content pane.
		// JLabel yellowLabel = new JLabel();
		// yellowLabel.setOpaque(true);
		// yellowLabel.setBackground(new Color(248, 213, 131));
		// yellowLabel.setPreferredSize(new Dimension(200, 180));
		//
		// //Set the menu bar and add the label to the content pane.
		// frame.setJMenuBar(greenMenuBar);
		// frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);
		//
		// //Display the window.
		// frame.pack();
		// frame.setVisible(true);
		//
		// JPanel contentPane = new JPanel(new BorderLayout());
		// contentPane.setBorder(someBorder);
		// contentPane.add(someComponent, BorderLayout.CENTER);
		// contentPane.add(anotherComponent, BorderLayout.PAGE_END);

	}
}
