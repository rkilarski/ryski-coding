package edu.metcs683.walkabout.dao;

import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Abstract class for all database (DAO) classes. This class will take care of
 * creating the database and all of its tables.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 * @param <T>
 */
public abstract class Database<T> extends SQLiteOpenHelper {

	public static final String DB_NAME = "edu.metcs683.walkabout";

	public static final int DB_VERSION = 1;
	private static final String CLASSNAME = Database.class.getSimpleName();

	private static final String DATABASE_TABLE_NAME_WAYPOINT = "waypoint";
	private static final String DATABASE_TABLE_NAME_WAYPOINT_IMAGE = "waypoint_image";

	private static final String TABLE_CREATE_STRING_WAYPOINT = "CREATE TABLE "
			+ DATABASE_TABLE_NAME_WAYPOINT
			+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT, dateTime TEXT, isExpanded TEXT, latitude TEXT, longitude TEXT);";

	private static final String TABLE_CREATE_STRING_WAYPOINT_IMAGE = "CREATE TABLE "
			+ DATABASE_TABLE_NAME_WAYPOINT_IMAGE
			+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT, waypointId INTEGER NOT NULL, imageURI TEXT);";

	public Database(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	/**
	 * Given an id, delete the item from the database.
	 * 
	 * @param id
	 */
	abstract public void delete(long id);

	/**
	 * Delete all rows from the database.
	 */
	abstract public void deleteAll();

	/**
	 * Given an id, delete all items associated with that id from the database.
	 * 
	 * @param id
	 */
	abstract public void deleteAll(long id);

	/**
	 * Given an id, get the item from the database.
	 * 
	 * @param id
	 * @return
	 */
	abstract public T get(long id);

	/**
	 * Get all of these items from the database, in no given order.
	 * 
	 * @param orderAscending
	 * @return
	 */
	abstract public List<T> getAll();
	
	/**
	 * Get all of these items from the database, in a given order.
	 * 
	 * @param orderAscending
	 * @return
	 */
	abstract public List<T> getAll(boolean orderAscending);

	/**
	 * Get all the items associated with an id, in a given order.
	 * 
	 * @param orderAscending
	 * @param id
	 * @return
	 */
	abstract public List<T> getAll(boolean orderAscending, long id);

	/**
	 * Insert the object into the database, and return its new id.
	 * 
	 * @param object
	 * @return
	 */
	abstract public long insert(T object);

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(TABLE_CREATE_STRING_WAYPOINT);
			db.execSQL(TABLE_CREATE_STRING_WAYPOINT_IMAGE);
		} catch (final SQLException e) {
			Log.e("ProviderWidgets", CLASSNAME, e);
		}
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME_WAYPOINT);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME_WAYPOINT_IMAGE);
		this.onCreate(db);
	}

	/**
	 * Update the object in the database.
	 * 
	 * @param object
	 */
	abstract public void update(T object);

}
