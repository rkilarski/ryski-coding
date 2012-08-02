package com.guitariffic.model;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * This is the table that contains the lyrics.
 * 
 * @author ryszardkilarski
 * 
 */
public class TextTable extends JTable {
	private static final long serialVersionUID = 1L;

	public TextTable() {
		super();
	}

	public TextTable(AbstractTableModel model) {
		super(model);
		setDefaultRenderer(MusicChart.class, new TextTableRenderer());

	}

	private class TextTableRenderer extends JLabel implements TableCellRenderer {
		private static final long serialVersionUID = 1L;

		public TextTableRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			this.setText(((TextItem) value).getText());

			TextType type = ((TextItem) value).getType();
			if (type == TextType.CHORD) {

			} else if (type == TextType.LABEL) {

			} else { // type == TextType.LYRIC

			}

			return this;
		}
	}
}
