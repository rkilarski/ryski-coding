package edu.metcs683.walkabout.dao;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.metcs683.walkabout.model.Waypoint;

/**
 * Data access object for the waypoint. This class controls the
 * loading/saving/querying of waypoints in the database.
 * 
 * @author Ryszard Kilarski
 * 
 */

public class WaypointDAO extends SQLiteOpenHelper implements Database<Waypoint> {

	private static final String DATABASE_TABLE_NAME = "waypoint";
	private static final String CLASSNAME = WaypointDAO.class.getSimpleName();
	private static final String[] COLUMN_LIST = new String[] { "_id",
			"description", "dateTime", "isExpanded", "latitude", "longitude" };
	private static final String DATABASE_CREATE_STRING = "CREATE TABLE "
			+ DATABASE_TABLE_NAME
			+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT, dateTime TEXT, isExpanded SMALLINT, latitude TEXT, longitude TEXT);";
	private static SQLiteDatabase db;

	public WaypointDAO(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
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
		db.delete(DATABASE_TABLE_NAME, "_id=" + id, null);
	}

	@Override
	public Waypoint get(long id) {
		Cursor cursor = null;
		Waypoint waypoint = null;
		try {
			cursor = db.query(true, DATABASE_TABLE_NAME, COLUMN_LIST, "_id = '"
					+ id + "'", null, null, null, null, null);
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				waypoint = getWaypointFromCursor(cursor);
			}
		} catch (SQLException e) {
			Log.v("ProviderWidgets", CLASSNAME, e);
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return waypoint;
	}

	@Override
	public List<Waypoint> getAll(boolean orderAscending) {
		List<Waypoint> waypoints = new ArrayList<Waypoint>();
		Cursor cursor = null;
		try {
			String orderBy = null;
			if (!orderAscending) {
				orderBy += "_id DESC";
			}
			cursor = db.query(DATABASE_TABLE_NAME, COLUMN_LIST, null, null,
					null, null, orderBy);
			int numRows = cursor.getCount();
			cursor.moveToFirst();
			for (int i = 0; i < numRows; ++i) {
				Waypoint waypoint = getWaypointFromCursor(cursor);
				waypoints.add(waypoint);
				cursor.moveToNext();
			}
		} catch (SQLException e) {
			Log.v("ProviderWidgets", CLASSNAME, e);
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return waypoints;
	}

	@Override
	public List<Waypoint> getAll(boolean orderAscending, long id) {
		// This is deliberately non functional.
		return null;
	}

	@Override
	public long insert(Waypoint waypoint) {
		ContentValues values = getContentValuesFromWaypoint(waypoint);
		return db.insert(DATABASE_TABLE_NAME, null, values);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DATABASE_CREATE_STRING);
		} catch (SQLException e) {
			Log.e("ProviderWidgets", CLASSNAME, e);
		}
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
		onCreate(db);
	}

	@Override
	public void update(Waypoint waypoint) {
		ContentValues values = getContentValuesFromWaypoint(waypoint);
		db.update(DATABASE_TABLE_NAME, values, "_id=" + waypoint.getId(), null);
	}

	private void establishDb() {
		if (db == null) {
			db = getWritableDatabase();
		}
	}

	private ContentValues getContentValuesFromWaypoint(Waypoint waypoint) {
		// TODO
		ContentValues values = new ContentValues();
		values.put("description", waypoint.getDescription());
		values.put("dateTime", getStringFromDate(waypoint.getDateTime()));
		values.put("latitude", waypoint.getLatitude());
		values.put("longitude", waypoint.getLongitude());
		// values.put("isExpanded", waypoint.isExpanded() ? 1 : 0);
		return values;
	}

	private Date getDateFromString(String string) {
		if (string == null) {
			return new Date();
		}
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.US);
		try {
			date = formatter.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	private String getStringFromDate(Date date) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.US);
		return formatter.format(date);
	}

	private Waypoint getWaypointFromCursor(Cursor cursor) {
		return new Waypoint(cursor.getLong(0), cursor.getString(1),
				getDateFromString(cursor.getString(2)),
				(cursor.getInt(3) == 0 ? false : true),
				Double.parseDouble(cursor.getString(4)),
				Double.parseDouble(cursor.getString(5)));
	}

	@Override
	public void deleteAll() {
		db.delete(DATABASE_TABLE_NAME, null, null);
	}

	@Override
	public void deleteAll(long id) {
		delete(id);
	}

}
