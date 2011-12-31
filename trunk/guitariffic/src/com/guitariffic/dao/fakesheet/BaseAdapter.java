package com.guitariffic.dao.fakesheet;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.guitariffic.dao.fakesheet.adapter.DOCXAdapter;
import com.guitariffic.dao.fakesheet.adapter.HTMLAdapter;
import com.guitariffic.dao.fakesheet.adapter.SerialAdapter;
import com.guitariffic.dao.fakesheet.adapter.XMLAdapter;

/**
 * Superclass for any class that is to save and open a file.
 * 
 * @author ryszardkilarski
 * 
 */
public abstract class BaseAdapter
{
	public abstract Object openFile(File file) throws IOException, ClassNotFoundException;

	public abstract void saveFile(Object o, File file) throws IOException;

	public static BaseAdapter getAdapter(String fileType)
	{
		BaseAdapter adapter = null;
		if (fileType.equalsIgnoreCase("xml"))
		{
			adapter = new XMLAdapter();
		}
		if (fileType.equalsIgnoreCase("guitariffic"))
		{
			adapter = new SerialAdapter();
		}
		if (fileType.equalsIgnoreCase("html"))
		{
			adapter = new HTMLAdapter();
		}
		if (fileType.equalsIgnoreCase("docx"))
		{
			adapter = new DOCXAdapter();
		}
		return adapter;
	}

	public static void getSaveTypes(JFileChooser fileChooser)
	{
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML File", "xml"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("guitariffic File", "guitariffic"));
	}

	public static void getSaveAsTypes(JFileChooser fileChooser)
	{
		getSaveTypes(fileChooser); // Include the Save file types as well.

		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("HTML File", "html"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Word (docx)", "docx"));
		// fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF File", "pdf", "pdf"));
	}
}
