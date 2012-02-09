package com.guitariffic.view.adapter;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import com.guitariffic.model.MusicChart;

/**
 * Transfer handler for the table drag and drop functionality. This is to transfer data from the chord tray. Note that canImport() returns false which
 * means that you can move chords out of the chord tray, but not change the tray order.
 * 
 * @author ryszardkilarski
 * 
 */
public class ChordTrayTransferHandler extends TransferHandler
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

		MusicChart value = (MusicChart) table.getModel().getValueAt(row, col);
		Transferable transferable = null;
		try
		{
			transferable = (Transferable) value.getTransferData(new DataFlavor(MusicChart.class, "ChordChart"));
		} catch (UnsupportedFlavorException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return transferable;
	}

	public boolean canImport(TransferHandler.TransferSupport info)
	{
		return false;
	}

	public boolean importData(TransferSupport support)
	{
		return false;
	}
}
