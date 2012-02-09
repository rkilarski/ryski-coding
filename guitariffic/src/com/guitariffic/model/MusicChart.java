package com.guitariffic.model;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import com.guitariffic.model.enums.EnumChordChartType;

/**
 * Chord chart base class.
 * 
 * @author ryszardkilarski
 * 
 */
public abstract class MusicChart extends BaseModel implements Serializable, Transferable, Cloneable
{
	private transient BufferedImage chordImage;
	private boolean isChanged;
	private String chordName;
	private String chordPosition;
	private String chordFingering;
	private String chordFrets;

	private long Key;
	private static final long serialVersionUID = 1L;

	public String getChordName()
	{
		return chordName;
	}

	public void setChordName(String chordName)
	{
		setDirty(true);
		this.chordName = chordName;
	}

	public MusicChart()
	{
		setChordImage(null);
	}

	public abstract EnumChordChartType getChordChartType();

	public BufferedImage getChordImage()
	{
		return chordImage;
	}

	public long getKey()
	{
		return Key;
	}

	public void setKey(long key)
	{
		Key = key;
	}

	public void setChordImage(BufferedImage chordImage)
	{
		this.chordImage = chordImage;
	}

	public void setDirty(boolean isDirty)
	{
		isChanged = isDirty;
		super.setDirty(isDirty);
	}

	public boolean isChanged()
	{
		return isChanged;
	}

	@Override
	public Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	public void setChanged(boolean isChanged)
	{
		this.isChanged = isChanged;
	}

	public String getChordPosition()
	{
		return chordPosition;
	}

	public void setChordPosition(String chordPosition)
	{
		this.setDirty(true);
		this.chordPosition = chordPosition;
	}

	public String getChordFingering()
	{
		return chordFingering;
	}

	public void setChordFingering(String chordFingering)
	{
		setDirty(true);
		this.chordFingering = chordFingering;
	}

	public String getChordFrets()
	{
		return chordFrets;
	}

	public void setChordFrets(String chordFrets)
	{
		setDirty(true);
		this.chordFrets = chordFrets;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
	{
		try
		{
			return clone();
		} catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors()
	{
		DataFlavor[] flavors = new DataFlavor[1];
		flavors[0] = new DataFlavor(MusicChart.class, "ChordChart");
		return flavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor)
	{
		return true;
	}

	/**
	 * Outputs the image to a jpeg file.
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void writeImageToFile(String fileName) throws IOException
	{
		// write to file
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
		ImageWriter writer = (ImageWriter) writers.next();
		if (writer == null)
		{
			throw new RuntimeException("JPeg not supported.");
		}

		ImageOutputStream out = ImageIO.createImageOutputStream(new File(fileName));
		writer.setOutput(out);
		writer.write(this.getChordImage());
		out.close(); // close flushes buffer
	}

	/**
	 * Outputs the image given a File object rather than just a file name.
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void writeImageToFile(File fileToSave) throws IOException
	{
		// write to file
		ImageIO.write(this.getChordImage(), "jpg", fileToSave);
	}
}
