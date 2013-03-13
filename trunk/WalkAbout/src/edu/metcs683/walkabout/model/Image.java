package edu.metcs683.walkabout.model;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Bitmap image;
	private long waypointId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
