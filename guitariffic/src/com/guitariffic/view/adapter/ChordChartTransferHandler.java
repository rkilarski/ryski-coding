package com.guitariffic.view.adapter;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.AbstractTableModel;

import com.guitariffic.model.BaseChordChart;
import com.guitariffic.model.GuitarChordChart;

/**
 * Transfer handler for the table drag and drop functionality. This is to transfer data within the chord chart area. Note that canImport() returns
 * true which means that you can move chords between cells in the chord chart area.
 * 
 * @author ryszardkilarski
 * 
 */
public class ChordChartTransferHandler extends TransferHandler
{
	private static final long serialVersionUID = 1L;

	public int getSourceActions(JComponent c)
	{
		return DnDConstants.ACTION_COPY_OR_MOVE;
	}

	public Transferable createTransferable(JComponent comp)
	{
		JTable table = (JTable) comp;
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();

		BaseChordChart value = (BaseChordChart) table.getModel().getValueAt(row, col);
		Transferable transferable = null;
		try
		{
			transferable = (Transferable) value.getTransferData(new DataFlavor(BaseChordChart.class, "ChordChart"));
		} catch (UnsupportedFlavorException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		table.getModel().setValueAt(new GuitarChordChart(), row, col);
		return transferable;
	}

	public boolean canImport(TransferHandler.TransferSupport info)
	{
		return true;
	}

	public boolean importData(TransferSupport support)
	{
		if (!support.isDrop())
		{
			return false;
		}

		if (!canImport(support))
		{
			return false;
		}

		JTable table = (JTable) support.getComponent();
		AbstractTableModel tableModel = (AbstractTableModel) table.getModel();

		JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();

		int row = dl.getRow();
		int col = dl.getColumn();

		BaseChordChart data;
		try
		{
			data = (BaseChordChart) support.getTransferable().getTransferData(new DataFlavor(GuitarChordChart.class, "ChordChart"));
		} catch (UnsupportedFlavorException e)
		{
			return false;
		} catch (IOException e)
		{
			return false;
		}

		tableModel.setValueAt(data, row, col);

		return true;
	}
}
