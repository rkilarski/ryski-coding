package com.guitariffic.dao.fakesheet.adapter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;

import com.guitariffic.dao.fakesheet.BaseAdapter;
import com.guitariffic.model.FakeSheet;
import com.guitariffic.model.GuitarChordChart;

public class DOCXAdapter extends BaseAdapter
{

	@Override
	public Object openFile(File file) throws IOException, ClassNotFoundException
	{
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void saveFile(Object o, File file) throws IOException
	{
		FakeSheet fakeSheet = (FakeSheet) o;

		try
		{

			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

			// To get bold text, you must set the run's rPr@w:b,
			// so you can't use the createParagraphOfText convenience method

			// org.docx4j.wml.P p = wordMLPackage.getMainDocumentPart().createParagraphOfText("text");

			org.docx4j.wml.ObjectFactory factory = new org.docx4j.wml.ObjectFactory();
			org.docx4j.wml.P p = factory.createP();

			org.docx4j.wml.Text t = factory.createText();
			// Add song and artist name.
			t.setValue(fakeSheet.getSongName() + "-" + fakeSheet.getArtistName());

			org.docx4j.wml.R run = factory.createR();
			run.getContent().add(t);

			p.getContent().add(run);

			org.docx4j.wml.RPr rpr = factory.createRPr();
			org.docx4j.wml.BooleanDefaultTrue b = new org.docx4j.wml.BooleanDefaultTrue();
			b.setVal(true);
			rpr.setB(b);

			run.setRPr(rpr);

			// Optionally, set pPr/rPr@w:b
			// org.docx4j.wml.PPr ppr = factory.createPPr();
			// p.setPPr(ppr);
			// org.docx4j.wml.ParaRPr paraRpr = factory.createParaRPr();
			// ppr.setRPr(paraRpr);
			// rpr.setB(b);

			wordMLPackage.getMainDocumentPart().addObject(p);

			// Let's add a table
			int writableWidthTwips = wordMLPackage.getDocumentModel().getSections().get(0).getPageDimensions().getWritableWidthTwips();
			int cols = 2;
			int cellWidthTwips = new Double(Math.floor((writableWidthTwips / cols))).intValue();

			Tbl tbl = TblFactory.createTable(1, 2, cellWidthTwips);

			List<Object> rows = tbl.getEGContentRowContent();
			Tr row = (Tr) rows.get(0);
			List<Object> cells = row.getEGContentCellContent();
			Tc tc = (Tc) cells.get(0);

			// Lyrics
			for (String text : fakeSheet.getTextArea().getTextAreaArray())
			{
				tc.getEGBlockLevelElts().add(wordMLPackage.getMainDocumentPart().createParagraphOfText(text));
			}

			// Images
			tc = (Tc) cells.get(1);
			Vector<Vector<GuitarChordChart>> chordCharts = fakeSheet.getChordChartArea().getChordChartArea();
			for (Vector<GuitarChordChart> chartRow : chordCharts)
			{
				for (GuitarChordChart chart : chartRow)
				{
					BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, serializeObj(chart.getChordImage()));
					tc.getEGBlockLevelElts().add(imagePart);
				}
			}

			wordMLPackage.getMainDocumentPart().addObject(tbl);

			// Now save it
			// wordMLPackage.save(file);

		} catch (Docx4JException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static byte[] serializeObj(BufferedImage obj)
	{

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try
		{
			ImageIO.write(obj, "jpg", baos);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		byte[] bytesOut = baos.toByteArray();
		return bytesOut;
	}
}
