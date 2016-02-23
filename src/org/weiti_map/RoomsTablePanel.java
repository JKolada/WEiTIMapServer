package org.weiti_map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

class RoomsTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private RoomsJTable roomTable;
	private JLabel label;
	
	RoomsTablePanel(RoomsTableObject roomsTableObject) {
		super();
		roomTable = new RoomsJTable(roomsTableObject);
		label = new JLabel("Sale");
		configure();		
	}
	
	private void configure() {
		roomTable.getTableHeader().setReorderingAllowed(false);		
		LC layoutConstraints = new LC();
		layoutConstraints.setFillX(true);
		setLayout(new MigLayout(layoutConstraints));		
		add(label, "wrap");		
		add(roomTable.getTableHeader(), "wrap");
		add(roomTable);		
	}

}