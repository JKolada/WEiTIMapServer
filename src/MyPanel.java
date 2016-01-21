

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MyPanel extends JPanel implements ActionListener {

	private JPanel groupJPanel = new JPanel();
    private JTable planTable = new MyJTable();
	private JTextArea groupNameJTextArea = new JTextArea("Nazwa grupy:");
	private JTextArea groupNameJTextField = new JTextArea("wprowad� nazw�");
	private JButton insertJButton = new JButton("Wprowad�");
	
    public MyPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		groupJPanel.setLayout(new GridLayout(1, 2));
		
		groupNameJTextArea.setOpaque(true);
		groupNameJTextArea.setEditable(false);

		groupNameJTextField.setForeground(Color.GRAY);		
			
		groupJPanel.add(groupNameJTextArea);
		groupJPanel.add(groupNameJTextField);
		
		add(groupJPanel);
		
		insertJButton.setPreferredSize(new Dimension(50, 20));
		insertJButton.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(new JScrollPane(planTable));
		add(insertJButton);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
