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
 * @author Ryszard Kilarski
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
			+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT, dateTime TEXT, isExpanded SMALLINT, latitude TEXT, longitude TEXT);";

	private static final String TABLE_CREATE_STRING_WAYPOINT_IMAGE = "CREATE TABLE "
			+ DATABASE_TABLE_NAME_WAYPOINT_IMAGE
			+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT, waypointId INTEGER NOT NULL, imageURI TEXT);";

	public Database(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	abstract public void delete(long id);

	abstract public void deleteAll();

	abstract public void deleteAll(long id);

	abstract public T get(long id);

	abstract public List<T> getAll(boolean orderAscending);

	abstract public List<T> getAll(boolean orderAscending, long id);

	abstract public long insert(T object);

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(TABLE_CREATE_STRING_WAYPOINT);
			db.execSQL(TABLE_CREATE_STRING_WAYPOINT_IMAGE);
		} catch (SQLException e) {
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
		onCreate(db);
	}

	abstract public void update(T object);

}
