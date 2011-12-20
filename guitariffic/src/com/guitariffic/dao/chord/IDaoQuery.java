package com.guitariffic.dao.chord;

import java.util.List;

import org.tmatesoft.sqljet.core.SqlJetException;

import com.guitariffic.model.BaseChordChart;
//import com.guitariffic.tools.ChordChart;

/**
 * Database Query methods
 * 
 * @author rdbrmurray
 *
 */
public interface IDaoQuery {

    /**
     * Select chord chart
     * 
     * @param name
     * @param posit
     * @param expand
     * @return List<String>
     * @throws SqlJetException
     */
	public List<String> select(String name, String posit, boolean expand)
			throws SqlJetException;

	/**
	 * Insert chord chart
	 * 
	 * @param chart
	 * @return integer; 0 = success; all others = fail
	 * @throws SqlJetException
	 */
	public int insert(BaseChordChart chart) throws SqlJetException;

	/**
	 * Update existing chord chart
	 * 
	 * @param chart
	 * @throws SqlJetException
	 * @return integer; 0 = success; all others = fail
	 */
	public int update(BaseChordChart chart) throws SqlJetException;

	/**
	 * Delete chord chart
	 *
	 * @param chart
	 * @throws SqlJetException
	 * @return integer; 0 = success; all others = fail
	 */
	public int delete(BaseChordChart chart) throws SqlJetException;

}