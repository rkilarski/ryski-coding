package com.guitariffic.dao.fakesheet.adapter;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;

public class DOCX4JFontMapperAdapter extends Mapper
{
	protected static Logger log = Logger.getLogger(IdentityPlusMapper.class);

	public DOCX4JFontMapperAdapter()
	{
		super();

		if (System.getProperty("os.name").toLowerCase().indexOf("windows") < 0)
		{
			log.warn("WARNING! SubstituterWindowsPlatformImpl works best " + "on Windows.  To get good results on other platforms, you'll probably  "
					+ "need to have installed Windows fonts.");
		}

	}

	static
	{

		try
		{

			// PhysicalFonts.discoverPhysicalFonts();
			// PhysicalFont font = PhysicalFonts.getPhysicalFonts().get("Courier New");
			// URL fontUrl = new URL(font.toString());

			// PhysicalFonts.addPhysicalFont(fontUrl);

		} catch (Exception exc)
		{
			throw new RuntimeException(exc);
		}
	}

	/**
	 * Populate the fontMappings object. We make an entry for each of the documentFontNames.
	 * 
	 * @param documentFontNames
	 *            - the fonts used in the document
	 * @param wmlFonts
	 *            - the content model for the fonts part
	 * @throws Exception
	 */
	public void populateFontMappings(Map documentFontNames, org.docx4j.wml.Fonts wmlFonts) throws Exception
	{

		/*
		 * org.docx4j.wml.Fonts fonts is obtained as follows:
		 * 
		 * FontTablePart fontTablePart= wordMLPackage.getMainDocumentPart().getFontTablePart(); org.docx4j.wml.Fonts fonts =
		 * (org.docx4j.wml.Fonts)fontTablePart.getJaxbElement();
		 * 
		 * If the document doesn't have a font table,
		 * 
		 * org.docx4j.openpackaging.parts.WordprocessingML.FontTablePart fontTable = new
		 * org.docx4j.openpackaging.parts.WordprocessingML.FontTablePart(); fontTable.unmarshalDefaultFonts();
		 */

		// We need to make a map out of it.
		// List<Fonts.Font> fontList = wmlFonts.getFont();
		// Map<String, Fonts.Font> fontsInFontTable = new HashMap<String, Fonts.Font>();
		// for (Fonts.Font font : fontList ) {
		// fontsInFontTable.put( normalise(font.getName()), font );
		// }

		Iterator documentFontMapIterator = documentFontNames.entrySet().iterator();
		while (documentFontMapIterator.hasNext())
		{
			Map.Entry pairs = (Map.Entry) documentFontMapIterator.next();

			if (pairs.getKey() == null)
			{
				log.info("Skipped null key");
				continue;
				// pairs = (Map.Entry)documentFontMapIterator.next();
			}

			String documentFontname = (String) pairs.getKey();
			log.debug("Document font: " + documentFontname);

			if (PhysicalFonts.getPhysicalFonts().get(documentFontname) != null)
			{

				// An identity mapping; that is all
				// this class knows how to do!
				fontMappings.put(documentFontname, PhysicalFonts.getPhysicalFonts().get(documentFontname));
			} else
			{

				log.warn("- - No physical font for: " + documentFontname);
			}
		}
	}
}
