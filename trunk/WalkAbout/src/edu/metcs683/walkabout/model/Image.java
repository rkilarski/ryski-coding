package edu.metcs683.walkabout.model;

import java.io.Serializable;

import android.graphics.Bitmap;

/**
 * POJO that describes the image.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Bitmap image;
	private long waypointId;

	public Image(long id, long waypointId, Bitmap image) {
		this.id = id;
		this.image = image;
		this.waypointId = waypointId;
	}

	public long getId() {
		return id;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public long getWaypointId() {
		return waypointId;
	}

	public void setWaypointId(long waypointId) {
		this.waypointId = waypointId;
	}

}
