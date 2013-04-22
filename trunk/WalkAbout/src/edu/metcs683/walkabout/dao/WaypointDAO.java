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
import android.util.Log;
import edu.metcs683.walkabout.model.Waypoint;

/**
 * Data access object for the waypoint. This class controls the
 * loading/saving/querying of waypoints in the database.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */

public class WaypointDAO extends Database<Waypoint> {

	private static final String CLASSNAME = WaypointDAO.class.getSimpleName();
	private static final String[] COLUMN_LIST = new String[] { "_id", "description", "dateTime", "isExpanded",
			"latitude", "longitude" };
	private static final String DATABASE_TABLE_NAME = "waypoint";

	public WaypointDAO(Context context) {
		super(context);
	}

	/**
	 * Given an id, delete the item from the database.
	 * 
	 * @param id
	 */
	@Override
	public void delete(long id) {
		final SQLiteDatabase db = getWritableDatabase();
		db.delete(DATABASE_TABLE_NAME, "_id=" + id, null);
		db.close();
	}

	/**
	 * Delete all rows from the database.
	 */
	@Override
	public void deleteAll() {
		final SQLiteDatabase db = getWritableDatabase();
		db.delete(DATABASE_TABLE_NAME, null, null);
		db.close();
	}

	/**
	 * Given an id, delete all items associated with that id from the database.
	 * 
	 * @param id
	 */
	@Override
	public void deleteAll(long id) {
		delete(id);
	}

	/**
	 * Given an id, get the item from the database.
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Waypoint get(long id) {
		Cursor cursor = null;
		Waypoint waypoint = null;
		try {
			final SQLiteDatabase db = getReadableDatabase();
			cursor = db.query(true, DATABASE_TABLE_NAME, COLUMN_LIST, "_id = '" + id + "'", null, null, null, null,
					null);
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				waypoint = getWaypointFromCursor(cursor);
			}
		} catch (final SQLException e) {
			Log.v("ProviderWidgets", CLASSNAME, e);
		} finally {
			if ((cursor != null) && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return waypoint;
	}

	/**
	 * Get all the items associated, in no given order.
	 * 
	 * @return
	 */
	@Override
	public List<Waypoint> getAll() {
		return getAll(true);
	}
	
	/**
	 * Get all of these items from the database, in a given order.
	 * 
	 * @param orderAscending
	 * @return
	 */
	@Override
	public List<Waypoint> getAll(boolean orderAscending) {
		final List<Waypoint> waypoints = new ArrayList<Waypoint>();
		Cursor cursor = null;
		try {
			String orderBy = null;
			if (!orderAscending) {
				orderBy = COLUMN_LIST[0] + " DESC";
			}
			final SQLiteDatabase db = getReadableDatabase();
			cursor = db.query(DATABASE_TABLE_NAME, COLUMN_LIST, null, null, null, null, orderBy);
			final int numRows = cursor.getCount();
			cursor.moveToFirst();
			for (int i = 0; i < numRows; ++i) {
				final Waypoint waypoint = getWaypointFromCursor(cursor);
				waypoints.add(waypoint);
				cursor.moveToNext();
			}
		} catch (final SQLException e) {
			Log.v("ProviderWidgets", CLASSNAME, e);
		} finally {
			if ((cursor != null) && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return waypoints;
	}

	/**
	 * Get all the items associated with an id, in a given order.
	 * 
	 * @param orderAscending
	 * @param id
	 * @return
	 */
	@Override
	public List<Waypoint> getAll(boolean orderAscending, long id) {
		// This is deliberately non functional.
		return null;
	}

	/**
	 * Insert the object into the database, and return its new id.
	 * 
	 * @param object
	 * @return
	 */
	@Override
	public long insert(Waypoint waypoint) {
		final ContentValues values = getContentValuesFromWaypoint(waypoint);
		final SQLiteDatabase db = getWritableDatabase();
		final long countInserted = db.insert(DATABASE_TABLE_NAME, null, values);
		db.close();
		return countInserted;
	}

	/**
	 * Update the object in the database.
	 * 
	 * @param object
	 */
	@Override
	public void update(Waypoint waypoint) {
		final ContentValues values = getContentValuesFromWaypoint(waypoint);
		final SQLiteDatabase db = getWritableDatabase();
		db.update(DATABASE_TABLE_NAME, values, "_id=" + waypoint.getId(), null);
		db.close();
	}

	/**
	 * Build a content value for a given waypoint.
	 * 
	 * @param waypoint
	 * @return
	 */
	private ContentValues getContentValuesFromWaypoint(Waypoint waypoint) {
		// TODO
		final ContentValues values = new ContentValues();
		values.put(COLUMN_LIST[1], waypoint.getDescription());
		values.put(COLUMN_LIST[2], getStringFromDate(waypoint.getDateTime()));
		// values.put(COLUMN_LIST[4], waypoint.isExpanded() ? 1 : 0);
		values.put(COLUMN_LIST[4], waypoint.getLatitude());
		values.put(COLUMN_LIST[5], waypoint.getLongitude());
		return values;
	}

	/**
	 * Convert a string into a date.
	 * 
	 * @param string
	 * @return
	 */
	private Date getDateFromString(String string) {
		if (string == null) {
			return new Date();
		}
		Date date = null;
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		try {
			date = formatter.parse(string);
		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Convert a date into a string.
	 * 
	 * @param date
	 * @return
	 */
	private String getStringFromDate(Date date) {
		final Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		return formatter.format(date);
	}

	/**
	 * Get the waypoint from a Sql cursor.
	 * 
	 * @param cursor
	 * @return
	 */
	private Waypoint getWaypointFromCursor(Cursor cursor) {
		return new Waypoint(cursor.getLong(0), cursor.getString(1), getDateFromString(cursor.getString(2)),
				(cursor.getInt(3) == 0 ? false : true), Double.parseDouble(cursor.getString(4)),
				Double.parseDouble(cursor.getString(5)));
	}

}
