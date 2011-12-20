package com.guitariffic.dao.chord;

import org.tmatesoft.sqljet.core.SqlJetException;

/**
 * File DAO factory
 * @author rdbrmurray
 */
public class ChordFileDaoFactory extends AbstractDaoFactory {
	/* {author=RMurray} */

    /**
     * Create Data Access Database Object 
     * 
     */
	public ChordFileDao createDaoAccess() throws SqlJetException {
		return new ChordFileDao();
	}

    /**
     * Create Data Access Query Object 
     * 
     */
	public FileDaoQuery createDaoQuery() throws SqlJetException {
		return new FileDaoQuery();
	}

}