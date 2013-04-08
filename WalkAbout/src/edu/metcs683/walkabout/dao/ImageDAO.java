package edu.metcs683.walkabout.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.metcs683.walkabout.model.Image;

/**
 * Data access object for the image. This class controls the
 * loading/saving/querying of images in the database.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class ImageDAO extends SQLiteOpenHelper implements Database<Image> {

	private static final String DATABASE_TABLE_NAME = "waypoint_images";
	private static final String CLASSNAME = ImageDAO.class.getSimpleName();
	private static final String[] COLUMN_LIST = new String[] { "_id",
			"waypointId", "imageURI" };
	private static final String DATABASE_CREATE_STRING = "CREATE TABLE "
			+ DATABASE_TABLE_NAME
			+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT, waypointId INTEGER NOT NULL, imageURI TEXT);";
	private static SQLiteDatabase db;

	public ImageDAO(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		establishDb();
	}

	public void cleanup() {
		if (db != null) {
			db.close();
			db = null;
		}
	}

	public void delete(long id) {
		db.delete(DATABASE_TABLE_NAME, "_id=" + id, null);
	}

	/**
	 * Get a particular image.
	 */
	public Image get(long id) {
		Cursor cursor = null;
		Image image = null;
		try {
			cursor = db.query(true, DATABASE_TABLE_NAME, COLUMN_LIST, "_id = '"
					+ id + "'", null, null, null, null, null);
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				image = getImageFromCursor(cursor);
			}
		} catch (SQLException e) {
			Log.v("ProviderWidgets", CLASSNAME, e);
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return image;
	}

	@Override
	public List<Image> getAll(boolean orderAscending) {
		return null;
	}

	/**
	 * Get a list of images given a waypoint.
	 */
	public List<Image> getAll(boolean orderAscending, long id) {
		List<Image> images = new ArrayList<Image>();
		Cursor cursor = null;
		try {
			String orderBy = null;
			if (!orderAscending) {
				orderBy += "_id DESC";
			}
			cursor = db.query(DATABASE_TABLE_NAME, COLUMN_LIST,
					"waypointId = '" + id + "'", null, null, null, orderBy);
			int numRows = cursor.getCount();
			cursor.moveToFirst();
			for (int i = 0; i < numRows; ++i) {
				Image image = getImageFromCursor(cursor);
				images.add(image);
				cursor.moveToNext();
			}
		} catch (SQLException e) {
			Log.v("ProviderWidgets", CLASSNAME, e);
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return images;
	}

	public long insert(Image image) {
		ContentValues values = getContentValuesFromImage(image);
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
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
		onCreate(db);
	}

	/**
	 * Given an image, update it in the database.
	 */
	public void update(Image image) {
		ContentValues values = getContentValuesFromImage(image);
		db.update(DATABASE_TABLE_NAME, values, "_id=" + image.getId(), null);
	}

	private void establishDb() {
		if (db == null) {
			db = getWritableDatabase();
		}
	}

	private ContentValues getContentValuesFromImage(Image image) {
		ContentValues values = new ContentValues();
		values.put("waypointId", image.getWaypointId());
		values.put("image", image.getImageURI());
		return values;
	}

	private Image getImageFromCursor(Cursor cursor) {
		return new Image(cursor.getLong(0), cursor.getLong(1),
				cursor.getString(1));
	}

	@Override
	public void deleteAll() {
		db.delete(DATABASE_TABLE_NAME, null, null);
	}

	@Override
	public void deleteAll(long id) {
		try {
			db.delete(DATABASE_TABLE_NAME, "waypointId=" + id, null);
		} catch (Exception ex) {
			;
		}
	}

}