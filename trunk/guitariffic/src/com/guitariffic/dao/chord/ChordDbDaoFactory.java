package com.guitariffic.dao.chord;

import org.tmatesoft.sqljet.core.SqlJetException;

/**
 * Guitar Chord DataBase Factory
 * 
 * @author rdbrmurray
 */
public class ChordDbDaoFactory extends AbstractDaoFactory {
	/* {author=RMurray} */

	public ChordDbDao createDaoAccess()  throws SqlJetException {
		return new ChordDbDao();
	}

	public DbDaoQuery createDaoQuery() throws SqlJetException {
		return new DbDaoQuery();
	}

}