package edu.metcs683.walkabout.dao;

import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.metcs683.walkabout.model.Waypoint;

public class WaypointDAO extends SQLiteOpenHelper implements Database<Waypoint> {

	private static final String CLASSNAME = WaypointDAO.class.getSimpleName();
	public static final String DB_TABLE = "waypoint";
	private static SQLiteDatabase db;
	private static final String DB_CREATE = "CREATE TABLE "
			+ WaypointDAO.DB_TABLE
			+ " (_id INTEGER PRIMARY KEY, description TEXT, datetime DATE, isexpanded BOOLEAN, gpslocation INTEGER);";

	public WaypointDAO(Context context) {
		super(context, ImageDAO.DB_NAME, null, ImageDAO.DB_VERSION);
		establishDb();
	}

	public void cleanup() {
		if (db != null) {
			db.close();
			db = null;
		}
	}

	@Override
	public void delete(long id) {
		db.delete(ImageDAO.DB_TABLE, "_id=" + id, null);
	}

	private void establishDb() {
		if (db == null) {
			db = getWritableDatabase();
		}
	}

	@Override
	public Waypoint get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Waypoint object) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Waypoint> getAll(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Waypoint object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(WaypointDAO.DB_CREATE);
		} catch (SQLException e) {
			Log.e("ProviderWidgets", WaypointDAO.CLASSNAME, e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS " + WaypointDAO.DB_TABLE);
		onCreate(db);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}

}
