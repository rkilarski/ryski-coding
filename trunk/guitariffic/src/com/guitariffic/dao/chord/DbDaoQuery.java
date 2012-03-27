package com.guitariffic.dao.chord;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.ISqlJetTransaction;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import com.guitariffic.model.MusicChart;

public class DbDaoQuery implements IDaoQuery {

	private static final String DB_NAME = "database\\guitariffic.sqljet";
	private static final String TABLE_NAME = "Chords";
	// private static final String CHORD_ID = "ID";
	private static final String CHORD_NAME = "Name";
	private static final String CHORD_KEY = "Key";
	private static final String CHORD_POSITION = "Position";
	@SuppressWarnings("unused")
	private static final String CHORD_FRET = "Frets";
	private static final String CHORD_CHART = "ChordChart";
	private static final String NAME_INDEX = "chord_name_index";
	private static final String KEY_INDEX = "chord_key_index";
	private static final String PRIMARY_INDEX = "name_posit_index";

	private SqlJetDb db;

	public DbDaoQuery() throws SqlJetException {
		/*
		 * 1. check to see if guitariffic.sqljet exists a. if exists, great b. if not exists, create
		 * and initialize 2. initialize database object
		 */
		File dbFile = new File(DB_NAME);
		if (!dbFile.exists()) {
			// create and initialize
			dbFile = null;
			initializeDb();
		}

	}

	private void initializeDb() throws SqlJetException {
		// DONE Auto-generated method stub
		// open db for writing
		openDb(true);
		// set DB option that have to be set before running any transactions:
		// Set autovacuum flag. It's allowed only on new empty data base.
		// It can't be performed in active transaction.
		db.getOptions().setAutovacuum(true);

		// set DB option that have to be set in a transaction:
		db.runTransaction(new ISqlJetTransaction() {
			public Object run(SqlJetDb db) throws SqlJetException {
				db.getOptions().setUserVersion(1);
				return true;
			}
		}, SqlJetTransactionMode.WRITE);

		db.beginTransaction(SqlJetTransactionMode.WRITE);

		try {
			String createTableQuery =
					"CREATE TABLE " + TABLE_NAME + " (" + CHORD_KEY + " INTEGER NOT NULL UNIQUE, " // Key
																									// value
																									// from
																									// CRC
							+ CHORD_NAME + " TEXT NOT NULL, " // name - must be unique
							+ CHORD_POSITION + " TEXT NOT NULL, " // finger list
							+ CHORD_CHART + " TEXT NOT NULL, " // XML representation of chord
							+ "PRIMARY KEY (" + CHORD_NAME + ", " + CHORD_POSITION + ")" + " )";

			String createChordNameIndexQuery =
					"CREATE INDEX " + NAME_INDEX + " ON " + TABLE_NAME + "( " + CHORD_NAME + ")";

			String createChordKeyIndexQuery =
					"CREATE INDEX " + KEY_INDEX + " ON " + TABLE_NAME + "( " + CHORD_KEY + ")";

			String createChordPrimaryIndexQuery =
					"CREATE INDEX " + PRIMARY_INDEX + " ON " + TABLE_NAME + "( " + CHORD_NAME
							+ ", " + CHORD_POSITION + ")";

			db.createTable(createTableQuery);
			db.createIndex(createChordNameIndexQuery);
			db.createIndex(createChordKeyIndexQuery);
			db.createIndex(createChordPrimaryIndexQuery);

		} finally {
			db.commit();
		}

		// insert rows:
		db.beginTransaction(SqlJetTransactionMode.WRITE);
		try {
			ISqlJetTable table = db.getTable(TABLE_NAME);
			// id (generated); name (i); fret (i); chord XML
			// insert charts:
			List<MusicChart> list = DataAccessUtilities.getNewChordChartList();

			for (MusicChart c : list) {
				String xml = DataAccessUtilities.getXStream(c);
				table.insert(c.getKey(), c.getChordName(), c.getChordPosition(), xml);
			}

		} finally {
			db.commit();
		}

		db.close();
		db = null;

	}

	/**
	 * @throws SqlJetException
	 */
	private void openDb(boolean isWritable) throws SqlJetException {

		File dbFile = new File(DB_NAME);
		// create database, table and two indices:
		// open(java.io.File file, boolean write)
		if (db == null) {
			db = SqlJetDb.open(dbFile, isWritable);
		}
	}

	@Override
	/**
	 * Returns list of strings representing chord charts
	 * 
	 * @see com.guitariffic.dao.IDaoQuery#select(java.lang.String)
	 * 
	 * @param key a Regex pattern for searching for items
	 * 
	 */
	public List<String> select(String name, String posit, boolean expand) throws SqlJetException {

		List<String> records = null;

		// System.out.println();
		// System.out.println(">All chords in order defined by " +
		// PRIMARY_INDEX);
		// System.out.println();

		// open db for reading
		openDb(false);

		String regex = expand ? ".*" : "";

		Pattern p = Pattern.compile(name + regex + posit + regex, Pattern.CASE_INSENSITIVE);

		// read
		ISqlJetTable table = db.getTable(TABLE_NAME);
		db.beginTransaction(SqlJetTransactionMode.READ_ONLY);
		try {
			ISqlJetCursor cursor = table.order(PRIMARY_INDEX);
			if (!cursor.eof()) {
				records = new ArrayList<String>();
				do {
					String matchPattern =
							cursor.getString(CHORD_NAME) + cursor.getString(CHORD_POSITION);
					Matcher m = p.matcher(matchPattern);
					if (m.matches()) {
						records.add(cursor.getString(CHORD_CHART));
					}
				} while (cursor.next());
			}
			cursor.close();
		} finally {
			db.commit();
		}
		// add to list

		// close database
		db.close();
		db = null;

		return records;
	}

	@Override
	public int insert(MusicChart chart) throws SqlJetException {

		int retVal = 0;
		// open db for writing
		openDb(true);

		try {

			// set DB option that have to be set in a transaction:
			db.runTransaction(new ISqlJetTransaction() {
				public Object run(SqlJetDb db) throws SqlJetException {
					db.getOptions().setUserVersion(1);
					return true;
				}
			}, SqlJetTransactionMode.WRITE);

			db.beginTransaction(SqlJetTransactionMode.WRITE);

			if (!DataAccessUtilities.validChord(chart)) {
				retVal = -10;
			} else {
				// name
				String chordName = chart.getChordName();
				// position
				String chordPosition = chart.getChordPosition();
				// key
				long key = DataAccessUtilities.getKeyId(chordName, chordPosition);
				chart.setKey(key);
				// XML
				String chordXml = DataAccessUtilities.getXStream(chart);

				ISqlJetTable table = db.getTable(TABLE_NAME);
				table.insert(key, chordName, chordPosition, chordXml);
			}

		} catch (SqlJetException e) {
			System.out.println(e.getMessage());
			retVal = -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			retVal = -1;
		} finally {
			if (retVal == 0)
				db.commit();
			else
				db.rollback();
		}

		db.close();
		db = null;

		return retVal;
	}

	@Override
	public int update(MusicChart chart) throws SqlJetException {
		// System.out.println();
		// System.out.println(">update " + chart);
		// System.out.println();
		int retVal = 0;

		// open db for writing
		openDb(true);

		try {

			// set DB option that have to be set in a transaction:
			db.runTransaction(new ISqlJetTransaction() {
				public Object run(SqlJetDb db) throws SqlJetException {
					db.getOptions().setUserVersion(1);
					return true;
				}
			}, SqlJetTransactionMode.WRITE);

			db.beginTransaction(SqlJetTransactionMode.WRITE);

			if (!DataAccessUtilities.validChord(chart)) {
				retVal = -10;
			} else {
				// name
				String chordName = chart.getChordName();
				// position
				String chordPosition = chart.getChordPosition();
				// key
				long key = DataAccessUtilities.getKeyId(chordName, chordPosition);
				chart.setKey(key);
				// XML
				String chordXml = DataAccessUtilities.getXStream(chart);

				ISqlJetTable table = db.getTable(TABLE_NAME);

				ISqlJetCursor updateCursor = table.lookup(PRIMARY_INDEX, chordName, chordPosition);
				if (!updateCursor.eof()) {
					updateCursor.update(updateCursor.getValue(CHORD_KEY), chordName, chordPosition, chordXml);
				} else {
					retVal = -400;
				}
				updateCursor.close();
			}

		} catch (SqlJetException e) {
			System.out.println(e.getMessage());
			retVal = -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			retVal = -1;
		} finally {
			if (retVal == 0)
				db.commit();
			else
				db.rollback();
		}

		db.close();
		db = null;
		return retVal;
	}

	@Override
	public int delete(MusicChart chart) throws SqlJetException {
		// System.out.println();
		// System.out.println(">delete " + chart.getChordName());
		// System.out.println();
		int retVal = 0;

		// open db for writing
		openDb(true);

		try {

			// set DB option that have to be set in a transaction:
			db.runTransaction(new ISqlJetTransaction() {
				public Object run(SqlJetDb db) throws SqlJetException {
					db.getOptions().setUserVersion(1);
					return true;
				}
			}, SqlJetTransactionMode.WRITE);

			if (!DataAccessUtilities.validChord(chart)) {
				retVal = -10;
			} else {
				db.beginTransaction(SqlJetTransactionMode.WRITE);

				ISqlJetTable table = db.getTable(TABLE_NAME);

				ISqlJetCursor deleteCursor =
						table.lookup(PRIMARY_INDEX, chart.getChordName(), chart.getChordPosition());

				if (!deleteCursor.eof()) {
					deleteCursor.delete();
				} else {
					retVal = -400;
				}
				deleteCursor.close();
			}

		} catch (SqlJetException e) {
			System.out.println(e.getMessage());
			retVal = -1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		} finally {
			if (retVal == 0)
				db.commit();
			else
				db.rollback();
		}

		db.close();
		db = null;

		return retVal;
	}

}