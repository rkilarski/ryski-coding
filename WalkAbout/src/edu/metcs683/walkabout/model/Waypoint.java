package edu.metcs683.walkabout.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * POJO that describes the waypoint.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class Waypoint {

	private Date dateTime;
	private String description;
	private long id;
	private List<Image> images = new ArrayList<Image>();
	private boolean isExpanded;
	private double latitude;
	private double longitude;

	public Waypoint(long id, String description, Date dateTime,
			boolean isExpanded, double latitude, double longitude) {
		this.id = id;
		this.description = description;
		this.dateTime = dateTime;
		this.isExpanded = isExpanded;
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}

	public void addImage(Image image) {
		images.add(image);

	}

	public Date getDateTime() {
		return dateTime;
	}

	public String getDescription() {
		return description;
	}

	public long getId() {
		return id;
	}

	public List<Image> getImages() {
		return images;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void removeImage(Image image) {
		images.remove(image);
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return this.description;
	}
}
