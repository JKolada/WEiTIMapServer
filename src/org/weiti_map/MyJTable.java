package org.weiti_map;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class MyJTable extends JTable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8925549787570334079L;

	public MyJTable() {
		super(new DefaultPlanTableModel());
		configure();
	}

	public MyJTable(GroupPlanObject plan, char parzystosc) {
		super(new CustomPlanTableModel(plan, parzystosc));
		configure();
	}
	
	private void configure() {
		this.setFont(new Font("Arial", Font.BOLD, 15));
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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

	public void setGroupPlan(GroupPlanObject plan, char parzystosc) {
		// TODO Auto-generated method stub

		setValueAt("sadas", 3, 3);
		System.out.println("at least tried");
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        int rendererWidth = component.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
        return component;
    }

}
