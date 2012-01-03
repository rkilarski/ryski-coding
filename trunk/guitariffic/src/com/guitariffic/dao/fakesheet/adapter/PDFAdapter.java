package com.guitariffic.dao.fakesheet.adapter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import com.guitariffic.dao.fakesheet.BaseAdapter;
import com.guitariffic.model.FakeSheet;

public class PDFAdapter extends BaseAdapter
{

	@Override
	public Object openFile(File file) throws IOException, ClassNotFoundException
	{
		return null;
	}

	@Override
	public void saveFile(Object o, File file) throws IOException
	{
		DOCX4JAdapter documentGetter = new DOCX4JAdapter();
		WordprocessingMLPackage wordMLPackage = documentGetter.generateDocument((FakeSheet) o);

		// Set up converter
		org.docx4j.convert.out.pdf.PdfConversion c = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(wordMLPackage);
		((org.docx4j.convert.out.pdf.viaXSLFO.Conversion) c).setSaveFO(file);

		OutputStream os = new java.io.FileOutputStream(file);

		try
		{
			c.output(os, new PdfSettings());
		} catch (Docx4JException e)
		{
			e.printStackTrace();
		}
		return;
	}

}
