package com.guitariffic.view.adapter;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import com.guitariffic.model.MusicChart;

/**
 * Table renderer for the chord tables.
 * 
 * @author ryszardkilarski
 * 
 */
public class ChordTableRenderer extends JLabel implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	public ChordTableRenderer() {
		this.setOpaque(false);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		this.setText(null);
		{
			this.setIcon(new ImageIcon(((MusicChart) value).getChordImage()));
		}
		this.setHorizontalAlignment(SwingConstants.CENTER);

		return this;
	}
}
