package edu.metcs683.walkabout.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * POJO that describes the image.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class Image {

	private long id;
	private String imageURI;
	private long waypointId;
	private Bitmap image;

	public Image(long id, long waypointId, String image) {
		this.id = id;
		this.imageURI = image;
		this.waypointId = waypointId;
	}

	public long getId() {
		return id;
	}

	public String getImageURI() {
		return imageURI;
	}

	public void setImageURI(String image) {
		this.imageURI = image;
	}

	public long getWaypointId() {
		return waypointId;
	}

	public void setWaypointId(long waypointId) {
		this.waypointId = waypointId;
	}

	/**
	 * Load image corresponding to this object.
	 * 
	 * @param context
	 * @return
	 * @throws IOException
	 */
	public Bitmap getImage(Context context) throws IOException {
		if (this.image == null) {
			FileInputStream fis = null;
			try {
				fis = context.openFileInput(this.imageURI);
				byte[] data = new byte[fis.available()];
				while (fis.read(data) != -1) {
				}
				this.image = BitmapFactory
						.decodeByteArray(data, 0, data.length);
			} catch (IOException e) {
				Log.e("ReadFile", e.getMessage(), e);
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						// swallow
					}
				}
			}
		}
		return this.image;
	}
}
