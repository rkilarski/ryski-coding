package com.guitariffic.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import com.guitariffic.dao.fakesheet.BaseAdapter;
import com.guitariffic.model.enums.EnumTextAreaType;

/**
 * This class contains the model for the FakeSheet. It contains within it the text and chord chart areas and has methods to open and save fake sheets.
 * 
 * @author ryszardkilarski
 * 
 */
public class FakeSheet extends BaseModel implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String artistName;
	private String songName;
	private BaseTextArea textArea;
	private ChordChartArea chordChartArea;

	public BaseTextArea getTextArea()
	{
		return textArea;
	}

	public void setTextArea(BaseTextArea textArea)
	{
		this.textArea = textArea;
	}

	public ChordChartArea getChordChartArea()
	{
		return chordChartArea;
	}

	public void setChordChartArea(ChordChartArea chordChartArea)
	{
		this.chordChartArea = chordChartArea;
	}

	/**
	 * Constructor with no parameters
	 */
	public FakeSheet()
	{
		// initializeModel(EnumTextAreaType.TEXT);
		initializeModel(EnumTextAreaType.STYLED);
	}

	public FakeSheet(EnumTextAreaType type)
	{
		initializeModel(type);
		/*
		 * textArea = new TextArea(); chordChartArea = new ChordChartArea();
		 */
	}

	private void initializeModel(EnumTextAreaType type)
	{
		switch (type)
		{
			case TEXT:
				textArea = new TextArea();
				break;
			case TABLE:
			case STYLED:
				textArea = new StyledTextArea();
				break;
			case HTML:
			default:
				// throw new Exception("Unimplemented TextAreaType " + type);
				textArea = null;
		}

		chordChartArea = new ChordChartArea(10, 5); // TODO: MAGIC NUMBERS!!!

	}

	/**
	 * Given a file name and loader adapter, returns a new FakeSheet object.
	 * 
	 * @param file
	 * @param openAdapter
	 * @return FakeSheet
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static FakeSheet openFile(File file, BaseAdapter openAdapter) throws IOException, ClassNotFoundException
	{
		return (FakeSheet) openAdapter.openFile(file);
	}

	/**
	 * Given a fake sheet and a save adapter, saves it into a file on the system.
	 * 
	 * @param file
	 * @param saveAdapter
	 * @throws IOException
	 */
	public void saveFile(File file, BaseAdapter saveAdapter) throws IOException
	{
		saveAdapter.saveFile(this, file);
	}

	public String getArtistName()
	{
		return artistName;
	}

	public String getSongName()
	{
		return songName;
	}

	public boolean isDirty()
	{
		/*
		 * if (this.textArea.isDirty()) { return true; } if (this.chordChartArea.isDirty()) { return true; }
		 */

		return super.isDirty();
	}

	public void setDirty(boolean isDirty)
	{
		super.setDirty(isDirty);
		if (!isDirty)
		{
			// textArea.setDirty(isDirty);
			// chordArea.setDirty(isDirty);
		}
	}

	public void setArtistName(String artistName)
	{
		this.artistName = artistName;
	}

	public void setSongName(String songName)
	{
		this.songName = songName;
	}

}
