package org.weiti_map;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import org.weiti_map.db.MyDatabase;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = -4065376415133611061L;

	public MyFrame(MyDatabase mDB) {
		super("WEiTI Map - Administrator");
		add(new MyPanel(mDB, this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(450, 500); // TODO

		setMinimumSize(new Dimension(700, 600));		
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    setLocation(x, y);
		pack();
		setVisible(true);

	}
}
