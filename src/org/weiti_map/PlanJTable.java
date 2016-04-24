package org.weiti_map;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class PlanJTable extends JTable {

	private static final long serialVersionUID = 8925549787570334079L;
	private TableModel tableModel;

	public PlanJTable() {
		super();
		tableModel = new PlanTableModel();
		setModel(tableModel);
		configure();
	}

	private void configure() {
		this.setFont(new Font("Arial", Font.BOLD, 15));
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		setLayout(new BorderLayout());
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i = 0; i < tableModel.getColumnCount(); i++) {
			getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		setPreferredScrollableViewportSize(new Dimension(500, 70));
		setFillsViewportHeight(true);
//        getSelectionModel().addListSelectionListener(new RowListener());
//        getColumnModel().getSelectionModel().
//            addListSelectionListener(new ColumnListener());
	}

	void setGroupPlan(GroupPlanObject plan, char parzystosc) {
		setModel(new CustomPlanTableModel(plan, parzystosc));
	}
	
	@Override
	public	Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        int rendererWidth = component.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
        return component;
    }

	 void resetTable() {
		setModel(tableModel);		
	}

}
