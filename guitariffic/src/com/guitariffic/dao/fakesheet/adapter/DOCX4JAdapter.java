package com.guitariffic.dao.fakesheet.adapter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.RPr;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.Style;
import org.docx4j.wml.Styles;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblBorders;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;

import com.guitariffic.model.FakeSheet;
import com.guitariffic.model.GuitarChordChart;

public class DOCX4JAdapter
{

	public WordprocessingMLPackage generateDocument(FakeSheet fakeSheet)
	{
		WordprocessingMLPackage wordMLPackage;
		try
		{
			wordMLPackage = WordprocessingMLPackage.createPackage();

			// Set the default style font.
			MainDocumentPart mp = wordMLPackage.getMainDocumentPart();
			Styles styles = mp.getStyleDefinitionsPart().getJaxbElement();
			// ObjectFactory factory = Context.getWmlObjectFactory();
			org.docx4j.wml.ObjectFactory factory = new org.docx4j.wml.ObjectFactory();
			for (Style s : styles.getStyle())
			{
				// if (s.getName().getVal().equals(Font.STYLE_NORMAL))
				// {
				RPr rpr = s.getRPr();
				if (rpr == null)
				{
					rpr = factory.createRPr();
					s.setRPr(rpr);
				}
				RFonts rf = rpr.getRFonts();
				if (rf == null)
				{
					rf = factory.createRFonts();
					rpr.setRFonts(rf);
				}
				// This is where you set your font name.
				rf.setAscii("Courier New");
				// }
			}

			// Set up font mapper
			// Mapper fontMapper = new IdentityPlusMapper();
			Mapper fontMapper = new DOCX4JFontMapperAdapter();
			wordMLPackage.setFontMapper(fontMapper);
			// Example of mapping missing font Algerian to installed font Comic Sans MS
			PhysicalFont font = PhysicalFonts.getPhysicalFonts().get("Courier New");
			fontMapper.getFontMappings().put("Courier New", font);
			// font = PhysicalFonts.getPhysicalFonts().get("Calibri");
			// fontMapper.getFontMappings().put("Calibri", font);

			// To get bold text, you must set the run's rPr@w:b,
			// so you can't use the createParagraphOfText convenience method
			org.docx4j.wml.P p = factory.createP();

			// Add song and artist name.
			org.docx4j.wml.Text t = factory.createText();
			t.setValue(fakeSheet.getSongName() + "-" + fakeSheet.getArtistName());

			org.docx4j.wml.R run = factory.createR();
			run.getContent().add(t);

			p.getContent().add(run);

			org.docx4j.wml.RPr rpr = factory.createRPr();
			org.docx4j.wml.BooleanDefaultTrue b = new org.docx4j.wml.BooleanDefaultTrue();
			b.setVal(true);
			rpr.setB(b);

			run.setRPr(rpr);

			wordMLPackage.getMainDocumentPart().addObject(p);

			// Let's add a table
			int writableWidthTwips = wordMLPackage.getDocumentModel().getSections().get(0).getPageDimensions().getWritableWidthTwips();
			int cols = 2;
			int cellWidthTwips = new Double(Math.floor((writableWidthTwips / cols))).intValue();

			Tbl tbl = TblFactory.createTable(1, 2, cellWidthTwips);

			// Set table borders
			TblBorders borders = new TblBorders();
			CTBorder border = new CTBorder();
			border.setVal(STBorder.NONE);
			borders.setLeft(border);
			borders.setRight(border);
			borders.setTop(border);
			borders.setBottom(border);
			borders.setInsideH(border);
			borders.setInsideV(border);
			tbl.getTblPr().setTblBorders(borders);

			List<Object> rows = tbl.getContent();
			Tr row = (Tr) rows.get(0);
			List<Object> cells = row.getContent();
			Tc tc = (Tc) cells.get(0);

			// Lyrics
			for (String text : fakeSheet.getTextArea().getTextAreaArray())
			{
				tc.getContent().add(wordMLPackage.getMainDocumentPart().createParagraphOfText(text));
			}

			// Images
			tc = (Tc) cells.get(1);
			Vector<Vector<GuitarChordChart>> chordCharts = fakeSheet.getChordChartArea().getChordChartArea();
			for (Vector<GuitarChordChart> chartRow : chordCharts)
			{
				// p = factory.createP();

				for (GuitarChordChart chart : chartRow)
				{
					// BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage,
					// serializeObj(chart.getChordImage()));
					// Inline inline = imagePart.createImageInline(chart.getChordName(), chart.getChordName(), 0, 1, false);
					// run = factory.createR();
					// p.getContent().add(run);
					// org.docx4j.wml.Drawing drawing = factory.createDrawing();
					// run.getContent().add(drawing);
					// drawing.getAnchorOrInline().add(inline);
				}
				// tc.getContent().add(p);
			}

			wordMLPackage.getMainDocumentPart().addObject(tbl);
			return wordMLPackage;

		} catch (InvalidFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
