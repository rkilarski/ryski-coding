package com.guitariffic.controller;

import com.guitariffic.model.GuitarChordChart;
import com.guitariffic.view.adapter.ChordChartAreaTableAdapter;

/**
 * Controller for the Chord Chart Area.
 * 
 * @author Henry Lee
 * @version 1.1, Dec 2011
 */
public class ChordChartAreaController 
{
	
    private ChordChartAreaTableAdapter chordChartAreaAdapter;
    
    /**
     * Constructor.
     */
    public ChordChartAreaController() {
        chordChartAreaAdapter = new ChordChartAreaTableAdapter();
    }
    
    /**
     * Sets the ChordChartAreaAdapter table model to the input ChordChartAreaTableAdapter object.
     * 
     * @param chordChartArea
     *              ChordChartAreaTableAdapter which extends from AbstractTableModel and wraps the model.
     */
    public void setChordChartAreaAdapter(ChordChartAreaTableAdapter chordChartArea) {
        this.chordChartAreaAdapter = chordChartArea;
    }
    
    /**
     * Returns the ChordChartAreaTableAdapter wrapper for the model.
     * 
     * @return ChordChartAreaTableAdapter that contains the actual model
     */
    public ChordChartAreaTableAdapter getChordChartAreaAdapter() {
        return chordChartAreaAdapter;
    }
    
}
