package edu.metcs683.walkabout.model;

/**
 * POJO that describes the image.
 * 
  * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class Image {

	private final long id;
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
