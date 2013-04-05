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

	private long id;
	private String description;
	private Date dateTime;
	private boolean isExpanded;
	private double latitude;
	private double longitude;
	private List<Image> images = new ArrayList<Image>();

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

	public void removeImage(Image image) {
		images.remove(image);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return this.description;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
