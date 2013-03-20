package edu.metcs683.walkabout.model;

import java.io.IOException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

	public Bitmap getImage() throws IOException {
		URL url = new URL(imageURI);
		Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
		return bmp;
	}

	public Bitmap getImageSmall() {
		return null;
	}
}
