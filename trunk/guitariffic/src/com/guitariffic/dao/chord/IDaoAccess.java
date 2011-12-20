package com.guitariffic.dao.chord;

import java.util.LinkedList;
import java.util.List;

import org.tmatesoft.sqljet.core.SqlJetException;

import com.guitariffic.model.BaseChordChart;

/**
 * Access Data storage
 * 
 * @author rdbrmurray
 *
 */
public interface IDaoAccess {

    /**
     * Get all chord charts in data storage
     * @param query
     * @param type
     * @return LinkedList<BaseChordChart>
     * @throws SqlJetException
     */
	public LinkedList<BaseChordChart> getAllChordCharts(IDaoQuery query, String type)
			throws SqlJetException;

	/**
	 * Get single BaseChordChart
	 * @param query
	 * @param name
	 * @param posit
	 * @param type
	 * @return BaseChordChart
	 * @throws SqlJetException
	 */
	public BaseChordChart getChordChart(IDaoQuery query, String name, String posit,
			String type) throws SqlJetException;

	/**
	 * Get List of XML strings representing all chord charts in data storage
	 * 
	 * @param query
	 * @return List<String>
	 * @throws SqlJetException
	 */
	public List<String> getChordChartList(IDaoQuery query)
			throws SqlJetException;

	/**
	 * Insert chord chart into data storage
	 * 
	 * @param query
	 * @param chordChart
	 * @return boolean (success/failure)
	 * @throws SqlJetException
	 */
	public boolean insertChordChart(IDaoQuery query, BaseChordChart chordChart)
			throws SqlJetException;

	/**
	 * Update chord chart in data storage
	 * 
	 * @param query
	 * @param chordChart
	 * @return boolean (success/failure)
	 * @throws SqlJetException
	 */
	public boolean updateChordChart(IDaoQuery query, BaseChordChart chordChart)
			throws SqlJetException;

	/**
	 * Delete chord chart from data storage
	 * 
	 * @param query
	 * @param chart
	 * @return boolean (success/failure)
	 * @throws SqlJetException
	 */
	public boolean deleteChordChart(IDaoQuery query, BaseChordChart chart)
			throws SqlJetException;

}