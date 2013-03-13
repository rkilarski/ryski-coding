package edu.metcs683.walkabout.dao;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import edu.metcs683.walkabout.model.Image;

public class ImageDAO extends SQLiteOpenHelper implements Database<Image> {

	private static final String CLASSNAME = ImageDAO.class.getSimpleName();
	private static final String[] COLS = new String[] { "_id", "waypointId",
			"image" };
	private static SQLiteDatabase db;
	private static final String DB_CREATE = "CREATE TABLE "
			+ ImageDAO.DB_TABLE
			+ " (_id INTEGER PRIMARY KEY, waypointId INTEGER NOT NULL, image BLOB);";

	public static final String DB_TABLE = "waypoint_images";
	public static final int DB_VERSION = 3;

	public static final String DEVICE_ALERT_ENABLED_ZIP = "DAEZ99";

	public ImageDAO(Context context) {
		super(context, ImageDAO.DB_NAME, null, ImageDAO.DB_VERSION);
		establishDb();
	}

	public void cleanup() {
		if (db != null) {
			db.close();
			db = null;
		}
	}

	public void delete(long id) {
		db.delete(ImageDAO.DB_TABLE, "_id=" + id, null);
	}

	private void establishDb() {
		if (db == null) {
			db = getWritableDatabase();
		}
	}

	/**
	 * Get a particular image.
	 */
	public Image get(long id) {
		Cursor cursor = null;
		Image image = null;
		try {
			cursor = db.query(true, ImageDAO.DB_TABLE, ImageDAO.COLS, "_id = '"
					+ id + "'", null, null, null, null, null);
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				image = new Image();
				image.setId(cursor.getLong(0));
				image.setWaypointId(cursor.getLong(1));
				image.setImage(this.getImageFromBLOB(cursor.getBlob(1)));
			}
		} catch (SQLException e) {
			Log.v("ProviderWidgets", ImageDAO.CLASSNAME, e);
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return image;
	}

	/**
	 * Get a list of images given a waypoint.
	 */
	public List<Image> getAll(long id) {
		List<Image> images = new ArrayList<Image>();
		Cursor cursor = null;
		try {
			cursor = db.query(ImageDAO.DB_TABLE, ImageDAO.COLS,
					"waypointId = '" + id + "'", null, null, null, null);
			int numRows = cursor.getCount();
			cursor.moveToFirst();
			for (int i = 0; i < numRows; ++i) {
				Image image = new Image();
				image = new Image();
				image.setId(cursor.getLong(0));
				image.setWaypointId(cursor.getLong(1));
				image.setImage(this.getImageFromBLOB(cursor.getBlob(1)));
				images.add(image);
				cursor.moveToNext();
			}
		} catch (SQLException e) {
			Log.v("ProviderWidgets", ImageDAO.CLASSNAME, e);
		} finally {
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return images;
	}

	public void insert(Image image) {
		ContentValues values = new ContentValues();
		values.put("waypointId", image.getWaypointId());
		values.put("image", this.getBLOBFromImage(image.getImage()));
		db.insert(ImageDAO.DB_TABLE, null, values);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(ImageDAO.DB_CREATE);
		} catch (SQLException e) {
			Log.e("ProviderWidgets", ImageDAO.CLASSNAME, e);
		}
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + ImageDAO.DB_TABLE);
		onCreate(db);
	}

	/**
	 * Given an image, update it in the database.
	 */
	public void update(Image image) {
		ContentValues values = new ContentValues();
		values.put("waypointId", image.getWaypointId());
		values.put("image", this.getBLOBFromImage(image.getImage()));
		db.update(ImageDAO.DB_TABLE, values, "_id=" + image.getId(), null);
	}

	private Bitmap getImageFromBLOB(byte[] mBlob) {
		byte[] bb = mBlob;
		return BitmapFactory.decodeByteArray(bb, 0, bb.length);
	}

	private byte[] getBLOBFromImage(Bitmap image) {
		int size = image.getRowBytes() * image.getHeight();
		ByteBuffer buffer = ByteBuffer.allocate(size);
		image.copyPixelsToBuffer(buffer);
		byte[] bytes = new byte[size];
		try {
			buffer.get(bytes, 0, bytes.length);
		} catch (BufferUnderflowException e) {
			// always happens
		}
		return bytes;
	}

}