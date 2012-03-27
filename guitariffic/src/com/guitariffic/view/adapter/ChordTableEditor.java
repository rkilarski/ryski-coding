package com.guitariffic.view.adapter;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.guitariffic.model.GuitarChordChart;
import com.guitariffic.model.MusicChart;
import com.guitariffic.view.GuitarChordChartEditor;

/**
 * Cell editor for any of the chord table items.
 * 
 * @author ryszardkilarski
 * 
 */
public class ChordTableEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	private static final long serialVersionUID = 1L;

	private JButton button;
	private MusicChart chordChart;
	private GuitarChordChartEditor chordChartEditor;
	protected static final String EDIT = "edit";

	public ChordTableEditor(Frame frame) {
		button = new JButton();
		button.setActionCommand(EDIT);
		button.addActionListener(this);
		button.setBorderPainted(false);
		chordChartEditor = new GuitarChordChartEditor(frame);
	}

	@Override
	public Object getCellEditorValue() {
		return chordChart;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		chordChart = (MusicChart) value;
		button.setIcon(new ImageIcon(((MusicChart) value).getChordImage()));
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (EDIT.equals(e.getActionCommand())) {
			// The user has clicked the cell, so bring up the dialog.
			chordChartEditor.setGuitarChordChart((GuitarChordChart) chordChart);
			chordChartEditor.setVisible(true);
			// Make the renderer reappear.
			if (chordChartEditor.getGuitarChordChart() != null) {
				chordChart = chordChartEditor.getGuitarChordChart();
			}
			fireEditingStopped();
		} // else
			// { // User pressed dialog's "OK" button.
			// chordChart = chordChartEditor.getGuitarChordChart();
		// }
	}
}
