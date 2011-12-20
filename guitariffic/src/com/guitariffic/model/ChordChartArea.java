package com.guitariffic.model;

import java.io.Serializable;
import java.util.Vector;

/**
 * Model for the chord chart area.
 * 
 * @author Ryszard Kilarski & Henry Lee
 * @version 2.0, Dec 2011
 */
public class ChordChartArea extends BaseModel implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Vector<Vector<GuitarChordChart>> chordChartArea;

	/**
	 * Returns the Vector of Vectors with GuitarChordChart elements.
	 */
	public Vector<Vector<GuitarChordChart>> getChordChartArea()
	{
		return chordChartArea;
	}

	/**
	 * Sets the chordChartArea
	 * 
	 * @param chordChartArea
	 *             Vector of Vectors with GuitarChordChart elements
	 */
	public void setChordChartArea(Vector<Vector<GuitarChordChart>> chordChartArea)
	{
		this.chordChartArea = chordChartArea;
	}

	/**
	 * Constructor to create a 1x1 area.
	 */
	public ChordChartArea()
	{
		super();
		chordChartArea = new Vector<Vector<GuitarChordChart>>();
		chordChartArea.add(new Vector<GuitarChordChart>());
		chordChartArea.get(0).add(new GuitarChordChart());
	}

	/**
	 * Constructor specifying the rows and columns of the table model.
	 * 
	 * @param rows
	 *            number of rows in the table model
	 * @param cols
	 *            number of columns in the table model
	 */
	public ChordChartArea(int rows, int cols)
	{
		chordChartArea = new Vector<Vector<GuitarChordChart>>();

		for (int i = 0; i < rows; i++)
		{
			chordChartArea.add(new Vector<GuitarChordChart>());

			for (int j = 0; j < cols; j++)
			{
				chordChartArea.get(i).add(new GuitarChordChart());
			}
		}
	}

}
