package edu.metcs683.walkabout.model;

import java.util.Date;
import java.util.List;

public class Waypoint {

	private static final long serialVersionUID = 1L;

	private String description;
	private Date dateTime;
	private boolean isExpanded;
	private int gpsLocation;
	private List<Image> images;

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

	public int getGpsLocation() {
		return gpsLocation;
	}

	public void setGpsLocation(int gpsLocation) {
		this.gpsLocation = gpsLocation;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
