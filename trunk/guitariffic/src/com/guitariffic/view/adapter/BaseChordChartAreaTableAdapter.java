package com.guitariffic.view.adapter;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.guitariffic.model.ChordChartArea;
import com.guitariffic.model.GuitarChordChart;

/**
 * The base class for the chord chart area table model wrapper that holds chord chart objects.
 * Extends from AbstractTableModel and implements Serializable interface.
 * 
 * @see AbstractTableModel
 * @see ChordChartArea
 * 
 * @author Henry Lee
 * @version 1.0, Dec 2011
 * 
 */
public abstract class BaseChordChartAreaTableAdapter extends AbstractTableModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private ChordChartArea chordChartArea;

	/**
	 * Returns the chordChartArea model object.
	 * 
	 * @return The Chord Chart area.
	 */
	public ChordChartArea getChordChartArea() {
		return chordChartArea;
	}

	/**
	 * Sets the chordChartArea
	 * 
	 * @param ChordChartArea
	 *            Vector of Vectors with GuitarChordChart elements
	 */
	public void setChordChartArea(ChordChartArea chordChartArea) {
		this.chordChartArea = chordChartArea;
	}

	/**
	 * Constructor
	 */
	public BaseChordChartAreaTableAdapter() {
		chordChartArea = new ChordChartArea();
	}

	/**
	 * Constructor that uses the passed in ChordChartArea object as its ChordChartArea.
	 * 
	 * @param chordChartArea
	 *            Vector of Vector of GuitarChordChart objects
	 */
	public BaseChordChartAreaTableAdapter(ChordChartArea chordChartArea) {
		this.chordChartArea = chordChartArea;
	}

	/**
	 * Returns the object class for the column col.
	 * 
	 * @param col
	 *            the column being queried
	 * @return the Object.class (GuitarChordChart)
	 */
	@Override
	public Class<?> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}

	/**
	 * Returns the number of columns in the table model.
	 * 
	 * @return number of columns
	 */
	@Override
	public int getColumnCount() {
		return chordChartArea.getChordChartArea().get(0).size();
	}

	/**
	 * Returns a null string for the column name because the model does not have column headers.
	 * 
	 * @param col
	 *            the column being queried
	 * @return null
	 */
	@Override
	public String getColumnName(int col) {
		return null;
	}

	/**
	 * Returns the number of rows in the table model.
	 * 
	 * @return number of rows
	 */
	@Override
	public int getRowCount() {
		return chordChartArea.getChordChartArea().size();
	}

	/**
	 * Returns the object at the location specified by row and col
	 * 
	 * @param row
	 *            the row being queried
	 * @param col
	 *            the column being queried
	 * @return Object (GuitarChordChart)
	 */
	@Override
	public Object getValueAt(int row, int col) {
		return chordChartArea.getChordChartArea().get(row).get(col);
	}

	/**
	 * Returns true (cell is editable) for all parameters
	 * 
	 * @param row
	 *            the row being queried
	 * @param col
	 *            the column being queried
	 * @return always returns true
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	/**
	 * Sets the value in the cell at row, col to value.
	 * 
	 * @param value
	 *            the new Object
	 * @param row
	 *            the row whose value is to be changed
	 * @param col
	 *            the column whose value is to be changed
	 */
	@Override
	public void setValueAt(Object value, int row, int col) {
		chordChartArea.getChordChartArea().get(row).setElementAt((GuitarChordChart) value, col);

		fireTableCellUpdated(row, col);
	}

	/**
	 * Adds a row as the last row of the table model.
	 */
	public void addRow() {
		int lastRow = getRowCount() - 1;

		chordChartArea.getChordChartArea().add(new Vector<GuitarChordChart>());

		for (int j = 0; j < getColumnCount(); j++) {
			chordChartArea.getChordChartArea().get(lastRow).add(new GuitarChordChart());
		}

		fireTableDataChanged();
	}

	/**
	 * Inserts a new row above the row specified by index. All rows beneath the index will get
	 * pushed down.
	 * 
	 * @param index
	 *            the row above which a new row will be added.
	 */
	public void insertRow(int index) {
		// int rows = chordChartTable.size();

		// if (index == row) {
		// chordChartTable.add( new Vector<GuitarChordChart>() );
		// } else {
		chordChartArea.getChordChartArea().insertElementAt(new Vector<GuitarChordChart>(), index);
		// }

		for (int j = 0; j < getColumnCount(); j++) {
			chordChartArea.getChordChartArea().get(index).add(new GuitarChordChart());
		}

		fireTableDataChanged();
	}

	/**
	 * Removes the row at the index specified.
	 * 
	 * @param index
	 *            the row to be removed
	 */
	public void removeRow(int index) {
		try {
			chordChartArea.getChordChartArea().remove(index);
		} catch (IndexOutOfBoundsException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
