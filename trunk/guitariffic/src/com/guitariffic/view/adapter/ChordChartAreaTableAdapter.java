package com.guitariffic.view.adapter;

import java.awt.Component;

import com.guitariffic.model.ChordChartArea;

/**
 * The chord chart area table model wrapper that holds guitar chord chart objects. Extends BaseChordChartArea.
 * 
 * @see BaseChordChartAreaTableAdapter
 * 
 * @author Henry Lee
 * @version 1.0, Dec 2011
 */
public class ChordChartAreaTableAdapter extends BaseChordChartAreaTableAdapter
{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that creates a 1 x 1 table model. Calls the parent constructor.
	 */
	public ChordChartAreaTableAdapter()
	{
		super();
	}

    /**
     * Constructor that uses the passed in ChordChartArea object as its ChordChartArea.
     * 
     * @param chordChartArea
     *              Vector of Vector of GuitarChordChart objects
     */
	public ChordChartAreaTableAdapter(ChordChartArea chordChartArea)
	{
		super(chordChartArea);
	}

	/**
	 * Returns null. Placeholder for possible future functionality.
	 * 
	 * @return null
	 */
	public ChordChartArea getChordChartArea()
	{
		return null;
	}

	/**
	 * Doesn't do anything. Placeholder for possible future functionality.
	 * 
	 * @param chordChartArea
	 *            an instance of ChordChartArea being passed in.
	 */
	public void setChordChartArea(Component chordChartArea)
	{

	}

}
