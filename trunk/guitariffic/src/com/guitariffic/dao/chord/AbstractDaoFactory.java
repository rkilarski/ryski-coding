package com.guitariffic.dao.chord;

import org.tmatesoft.sqljet.core.SqlJetException;

/**
 * Abstract class of type DAO Factory - returns type of factory requested.
 * 
 * @author rmurray
 * 
 */
public abstract class AbstractDaoFactory {
	/* {author=RMurray} */

	public static final String database = "db";
	public static final String file = "file";
	
	/**
	 * Create Data Access Object 
	 * @return IDaoAccess
	 * @throws SqlJetException
	 */
	public abstract IDaoAccess createDaoAccess() throws SqlJetException;

	/**
	 * Return Data Access Query object
	 * @return IDaoAccess
	 * @throws SqlJetException
	 */
	public abstract IDaoQuery createDaoQuery() throws SqlJetException;

}