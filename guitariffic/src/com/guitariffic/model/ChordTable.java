package com.guitariffic.model;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.guitariffic.view.adapter.ChordChartAreaTableAdapter;
import com.guitariffic.view.adapter.ChordTableEditor;

/**
 * Table object for each of the chord display areas.
 * 
 * @author ryszardkilarski
 * 
 */
public class ChordTable extends JTable {

	private static final long serialVersionUID = 1L;
	private int hoverColumn = -1;
	private int hoverRow = -1;
	private boolean showHover = false;

	public ChordTable() {
		super();
	}

	public ChordTable(AbstractTableModel model, JFrame frame) {
		super(model);
		setDefaultEditor(MusicChart.class, new ChordTableEditor(frame));
		setDefaultRenderer(MusicChart.class, new ChordTableRenderer());

	}

	public ChordTable(ChordChartAreaTableAdapter chordChartAreaAdapter, JFrame frame) {
		super(chordChartAreaAdapter);
		setDefaultEditor(MusicChart.class, new ChordTableEditor(frame));
		setDefaultRenderer(MusicChart.class, new ChordTableRenderer());

	}

	/**
	 * @return the hoverColumn
	 */
	public int getHoverColumn() {
		return hoverColumn;
	}

	/**
	 * @return the hoverRow
	 */
	public int getHoverRow() {
		return hoverRow;
	}

	/**
	 * @param hoverColumn
	 *            the hoverColumn to set
	 */
	public void setHoverColumn(int hoverColumn) {
		this.hoverColumn = hoverColumn;
	}

	/**
	 * @param hoverRow
	 *            the hoverRow to set
	 */
	public void setHoverRow(int hoverRow) {
		this.hoverRow = hoverRow;
	}

	public void setShowHover(boolean showHover) {
		this.showHover = showHover;
	}

	private class ChordTableRenderer extends JLabel implements TableCellRenderer {
		private static final long serialVersionUID = 1L;

		public ChordTableRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (showHover) {
				if (row == getHoverRow() && column == getHoverColumn()) {
					this.setBackground(Color.red);
					// this.setForeground(Color.blue);
				} else {
					this.setBackground(Color.white);
					// this.setForeground(Color.white);
				}
			}

			this.setText(null);
			{
				this.setIcon(new ImageIcon(((MusicChart) value).getChordImage()));
			}
			this.setHorizontalAlignment(SwingConstants.CENTER);

			return this;
		}
	}
}
