package com.guitariffic.model;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

/**
 * Model for the chord tray list.
 * 
 * @author ryszardkilarski
 * 
 */
public class ChordTrayTableModel extends AbstractTableModel
{

	private static final long serialVersionUID = 1L;
	private LinkedList<MusicChart> chordChartList;

	public ChordTrayTableModel()
	{
		chordChartList = null;
	}

	@Override
	public Class<?> getColumnClass(int arg0)
	{
		return chordChartList.get(arg0).getClass();
	}

	@Override
	public int getColumnCount()
	{
		return 1;
	}

	@Override
	public String getColumnName(int arg0)
	{
		return null;
	}

	@Override
	public int getRowCount()
	{
		return chordChartList.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1)
	{
		return chordChartList.get(arg0);
		// return chordChartList.get(arg0).getChordName();
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1)
	{
		return true;
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2)
	{
		chordChartList.set(arg1, (MusicChart) arg0);
		fireTableCellUpdated(arg1, arg2);
	}

	public LinkedList<MusicChart> getChordChartList()
	{
		return chordChartList;
	}

	public void setChordChartList(LinkedList<MusicChart> chordChartList)
	{
		this.chordChartList = chordChartList;
		fireTableDataChanged();
	}

	public void addChordChart(MusicChart chordChart)
	{
		chordChartList.addFirst(chordChart);
		fireTableDataChanged();
	}

}
