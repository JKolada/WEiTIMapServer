package org.weiti_map;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class MyJTable extends JTable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8925549787570334079L;

	public MyJTable() {
		super(new PlanTableModel());		

		this.setFont(new Font("Arial", Font.BOLD, 15));
//		setLayout(new BorderLayout());
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i = 0; i < 6; i++) {
			getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
        
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);
//        getSelectionModel().addListSelectionListener(new RowListener());
//        getColumnModel().getSelectionModel().
//            addListSelectionListener(new ColumnListener());

	}

}
