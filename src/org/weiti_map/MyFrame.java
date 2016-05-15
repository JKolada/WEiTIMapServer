package org.weiti_map;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.weiti_map.db.MyDatabase;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = -4065376415133611061L;

	public MyFrame(MyDatabase mDB) {
		super("WEiTIstrator");
		add(new MyPanel(mDB, this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(450, 500); // TODO
		setMinimumSize(new Dimension(1000, 600));
		pack();
		setVisible(true);

	}
}
