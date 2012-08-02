package com.guitariffic.model;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

/**
 * This is the tablemodel for the lyrics.
 * 
 * @author ryszardkilarski
 * 
 */
public class TextTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private LinkedList<TextItem> lyrics;

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return lyrics.get(arg0);
	}

	@Override
	public int getRowCount() {
		return lyrics.size();
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		lyrics.set(arg1, (TextItem) arg0);
		fireTableCellUpdated(arg1, arg2);
	}

	public LinkedList<TextItem> getLyricsList() {
		return lyrics;
	}

	public void setLyricsList(LinkedList<TextItem> lyrics) {
		this.lyrics = lyrics;
		fireTableDataChanged();
	}

	public void addText(String text, TextType type) {
		lyrics.addFirst(new TextItem(text, type));
		fireTableDataChanged();
	}

}
