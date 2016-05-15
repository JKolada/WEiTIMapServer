package org.weiti_map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

class CustomTablePanel<TableModelClass, TableObjectClass> extends JPanel {

	private static final long serialVersionUID = -619621154917011158L;
	private CustomJTable<TableModelClass, TableObjectClass> mTable;
	private JLabel mLabel;

	CustomTablePanel(String label, TableObjectClass tableObject,
			TableModelClass tableModel) {
		super();
		mTable = new CustomJTable<TableModelClass, TableObjectClass>(
				tableObject, tableModel);
		mLabel = new JLabel(label);
		configure();
	}

	private void configure() {
		mTable.getTableHeader().setReorderingAllowed(false);
		LC layoutConstraints = new LC();
		layoutConstraints.setFillX(true);
		setLayout(new MigLayout(layoutConstraints));
		add(mLabel, "wrap");
		add(mTable.getTableHeader(), "wrap");
		add(mTable);
	}

}