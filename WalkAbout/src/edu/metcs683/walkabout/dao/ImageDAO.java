package edu.metcs683.walkabout.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import edu.metcs683.walkabout.model.Image;

/**
 * Data access object for the image. This class controls the
 * loading/saving/querying of images in the database.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class ImageDAO extends Database<Image> {

	private static final String DATABASE_TABLE_NAME = "waypoint_image";
	private static final String CLASSNAME = ImageDAO.class.getSimpleName();
	private static final String[] COLUMN_LIST = new String[] { "_id",
			"waypointId", "imageURI" };

	public ImageDAO(Context context) {
		super(context);
	}

	public void delete(long id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(DATABASE_TABLE_NAME, "_id=" + id, null);
		db.close();
	}

	/**
	 * Get a particular image.
	 */
	public Image get(long id) {
		Cursor cursor = null;
		Image image = null;
		try {
			SQLiteDatabase db = this.getReadableDatabase();
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
				orderBy = COLUMN_LIST[0]+" DESC";
			}
			SQLiteDatabase db = this.getReadableDatabase();
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
		SQLiteDatabase db = this.getWritableDatabase();
		long countInserted = db.insert(DATABASE_TABLE_NAME, null, values);
		db.close();
		return countInserted;
	}

	/**
	 * Given an image, update it in the database.
	 */
	public void update(Image image) {
		ContentValues values = getContentValuesFromImage(image);
		SQLiteDatabase db = this.getWritableDatabase();
		db.update(DATABASE_TABLE_NAME, values, "_id=" + image.getId(), null);
		db.close();
	}

	private ContentValues getContentValuesFromImage(Image image) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_LIST[1], image.getWaypointId());
		values.put(COLUMN_LIST[2], image.getImageURI());
		return values;
	}

	private Image getImageFromCursor(Cursor cursor) {
		return new Image(cursor.getLong(0), cursor.getLong(1),
				cursor.getString(2));
	}

	@Override
	public void deleteAll() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(DATABASE_TABLE_NAME, null, null);
		db.close();
	}

	@Override
	public void deleteAll(long id) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(DATABASE_TABLE_NAME, "waypointId=" + id, null);
			db.close();
		} catch (Exception ex) {
			;
		}
	}

}