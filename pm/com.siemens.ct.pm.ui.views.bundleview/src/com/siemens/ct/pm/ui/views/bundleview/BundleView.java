package com.siemens.ct.pm.ui.views.bundleview;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import com.siemens.ct.pm.application.service.IViewContribution;

public class BundleView implements IViewContribution {

	private final ImageIcon icon;
	private final JComponent view;
	private final JTable table;
	private final Bundle[] bundles;

	@SuppressWarnings("serial")
	class TableModel extends AbstractTableModel {

		@Override
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "";

			case 1:
				return "#";

			case 2:
				return "Symbolic Name";

			case 3:
				return "State";

			default:
				return null;
			}
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public int getRowCount() {
			return bundles.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Bundle bundle = bundles[rowIndex];
			switch (columnIndex) {
			case 0:
				return bundle.getState() == Bundle.ACTIVE;

			case 1:
				return bundle.getBundleId();

			case 2:
				return bundle.getSymbolicName();

			case 3:
				switch (bundle.getState()) {
				case Bundle.ACTIVE:
					return "ACTIVE";
				case Bundle.INSTALLED:
					return "INSTALLED";
				case Bundle.RESOLVED:
					return "RESOLVED";
				case Bundle.UNINSTALLED:
					return "UNINSTALLED";
				default:
					return "UNKNOWN";
				}

			default:
				return null;
			}
		}

		@Override
		@SuppressWarnings("unchecked")
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		@Override
		public boolean isCellEditable(int row, int col) {
			if (col == 0) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void setValueAt(Object value, int row, int col) {
			Bundle selectedBundle = bundles[row];
			if (selectedBundle.getState() == Bundle.ACTIVE) {
				try {
					selectedBundle.stop();
				} catch (BundleException e1) {
					e1.printStackTrace();
				}
			} else if (selectedBundle.getState() == Bundle.RESOLVED) {
				try {
					selectedBundle.start();
				} catch (BundleException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public BundleView() {
		super();

		bundles = Activator.getBundles();

		icon = new ImageIcon(this.getClass().getResource("/icons/bundle.png"));
		table = new JTable(new TableModel());
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);

		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(0).setMaxWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setMaxWidth(25);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);

		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		view = new JScrollPane(table);
		view.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 2, 2,
				2), BorderFactory.createLineBorder(Color.lightGray)));
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

	@Override
	public String getName() {
		return "Bundle View";
	}

	@Override
	public JComponent getView() {
		return view;
	}
}
