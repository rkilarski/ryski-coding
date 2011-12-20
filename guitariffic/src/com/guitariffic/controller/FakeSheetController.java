package com.guitariffic.controller;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.guitariffic.dao.fakesheet.BaseAdapter;
import com.guitariffic.model.BaseTextArea;
import com.guitariffic.model.FakeSheet;
import com.guitariffic.view.adapter.ChordChartAreaTableAdapter;

/**
 * Controller class for the FakeSheet interactions. This class will keep a pointer to the FakeSheet object and do all the UI interactions as needed
 * from the View.
 * 
 * @author ryszardkilarski
 * 
 */
public class FakeSheetController
{

	private FakeSheet fakeSheet;
	private File fakeSheetFile;

	public FakeSheetController()
	{
		this.setFakeSheet(null);
		newFakeSheet();
	}

	public BaseTextArea getTextArea()
	{
		return fakeSheet.getTextArea();
	}

	public ChordChartAreaTableAdapter getChordChartArea()
	{
		return new ChordChartAreaTableAdapter(fakeSheet.getChordChartArea());
	}

	/**
	 * Method to open a fake sheet and make it the active fake sheet.
	 */
	public void openFakeSheet()
	{
		File file = null;

		// Check if we need to save any open fakesheet first.
		if (checkIfNeedsSaving())
		{
			if (JOptionPane.showConfirmDialog(null, "You have an unsaved Fake Sheet.  Would you like to save it?", "Save Fake Sheet", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				saveFakeSheet(false);
			}
		}

		file = promptForFile(true, false);
		if (file != null)
		{
			try
			{
				this.setFakeSheet(FakeSheet.openFile(file, BaseAdapter.getAdapter(getFileExtension(file.getName()))));
				this.setFakeSheetFile(file);
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create a new fake sheet. Check if any existing fake sheet needs to be saved first.
	 */
	public void newFakeSheet()
	{
		if (checkIfNeedsSaving())
		{
			if (JOptionPane.showConfirmDialog(null, "You have an unsaved Fake Sheet.  Would you like to save it?", "Save Fake Sheet", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				saveFakeSheet(false);
			}
		}

		this.setFakeSheet(new FakeSheet());
	}

	/**
	 * Prompt for filename if we haven't saved this already. Then, save the file.
	 * 
	 * @param saveAsFlag
	 *            If True, then this is a Save As function. If false, then Save function.
	 */
	public void saveFakeSheet(boolean saveAsFlag)
	{
		File file = null;

		// If not a SaveAs, get the native file name, if any.
		if (!saveAsFlag)
		{
			file = this.getFakeSheetFile();
		}
		if (file == null)
		{
			file = promptForFile(false, saveAsFlag);
		}

		if (file != null)
		{
			try
			{
				fakeSheet.saveFile(file, BaseAdapter.getAdapter(getFileExtension(file.getName())));
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			fakeSheet.setDirty(false);
			this.setFakeSheetFile(file);
		}
	}

	/**
	 * Check if we need to save an open FakeSheet.
	 * 
	 * @return
	 */
	private boolean checkIfNeedsSaving()
	{
		boolean needsSaving = false;
		if ((this.getFakeSheet() != null) && (this.getFakeSheet().isDirty()))
		{
			needsSaving = true;
		}
		return needsSaving;
	}

	private FakeSheet getFakeSheet()
	{
		return fakeSheet;
	}

	private File getFakeSheetFile()
	{
		return fakeSheetFile;
	}

	public String getFakeSheetFileName()
	{
		String fileName;
		if (this.fakeSheetFile == null)
		{
			fileName = "";
		} else
		{
			fileName = this.fakeSheetFile.getName();
		}
		return fileName;
	}

	/**
	 * Prompt the user for a file.
	 * 
	 * @param showOpenDialog
	 * @return
	 */
	private File promptForFile(boolean showOpenDialog, boolean saveAsFlag)
	{
		int returnValue;
		File file = null;
		final JFileChooser fileChooser = new JFileChooser();

		if (saveAsFlag)
		{
			BaseAdapter.getSaveAsTypes(fileChooser);
		} else
		{
			BaseAdapter.getSaveTypes(fileChooser);
		}

		if (showOpenDialog)
		{
			returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION)
			{
				file = fileChooser.getSelectedFile();
			}
		} else
		{
			returnValue = fileChooser.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION)
			{

				String[] extensions = ((FileNameExtensionFilter) fileChooser.getFileFilter()).getExtensions();

				if (this.getFileExtension(fileChooser.getSelectedFile().getName()) != extensions[0])
				{
					// Add extension if it didn't exist already
					file = new File(fileChooser.getSelectedFile().getAbsoluteFile() + "." + extensions[0]);
				} else
				{
					file = fileChooser.getSelectedFile();
				}
			}
		}
		return file;
	}

	private void setFakeSheet(FakeSheet fakeSheet)
	{
		this.fakeSheet = fakeSheet;
	}

	private void setFakeSheetFile(File fakeSheetFile)
	{
		this.fakeSheetFile = fakeSheetFile;
	}

	public String getArtistName()
	{
		return this.getFakeSheet().getArtistName();
	}

	public String getSongName()
	{
		return this.getFakeSheet().getSongName();
	}

	public void setArtistName(String artistName)
	{
		this.getFakeSheet().setArtistName(artistName);
	}

	public void setSongName(String songName)
	{
		this.getFakeSheet().setSongName(songName);
	}

	private String getFileExtension(String fileName)
	{
		int mid = fileName.lastIndexOf(".");
		return fileName.substring(mid + 1, fileName.length());
	}
}
