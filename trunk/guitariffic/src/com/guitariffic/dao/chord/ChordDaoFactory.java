package com.guitariffic.dao.chord;

/**
 * Data Access Object factory
 * returns Chord or File DaoFactory
 * 
 * @author rdbrmurray
 *
 */
public class ChordDaoFactory {

    /**
     * Build DAO factory for 'db' or 'file' types
     * 
     * @param type of factory requested
     * @return AbstractDaoFactory
     */
	public AbstractDaoFactory buildFactory(String type) {
		AbstractDaoFactory factory = null;
		if (type == "db") {
			factory = new ChordDbDaoFactory();
		} else if (type == "file") {
			factory = new ChordFileDaoFactory();
		}
		return factory;
	}

}