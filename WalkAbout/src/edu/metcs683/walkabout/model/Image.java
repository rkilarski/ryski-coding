package edu.metcs683.walkabout.model;

import java.io.FileInputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * POJO that describes the image.
 * 
 * @author ryszardkilarski
 * 
 */
public class Image {

	private final long id;
	private transient Bitmap image;
	private String imageURI;
	private long waypointId;

	public Image(long id, long waypointId, String image) {
		this.id = id;
		this.imageURI = image;
		this.waypointId = waypointId;
	}

	public long getId() {
		return id;
	}

	/**
	 * Load image corresponding to this object.
	 * 
	 * @param context
	 * @return
	 * @throws IOException
	 */
	public Bitmap getImage(Context context) throws IOException {
		if (image == null) {
			FileInputStream fis = null;
			try {
				fis = context.openFileInput(imageURI);
				final byte[] data = new byte[fis.available()];
				while (fis.read(data) != -1) {
				}
				image = BitmapFactory.decodeByteArray(data, 0, data.length);
			} catch (final IOException e) {
				Log.e("ReadFile", e.getMessage(), e);
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (final IOException e) {
						// Swallow this exception.
					}
				}
			}
		}
		return image;
	}

	public String getImageURI() {
		return imageURI;
	}

	public long getWaypointId() {
		return waypointId;
	}

	public void setImageURI(String image) {
		this.imageURI = image;
	}

	public void setWaypointId(long waypointId) {
		this.waypointId = waypointId;
	}
}
