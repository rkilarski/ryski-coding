package edu.metcs683.walkabout.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Waypoint implements Serializable{

	private static final long serialVersionUID = 1L;

	private String description;
	private Date dateTime;
	private boolean isExpanded;
	private int gpsLocation;
	private List<Image> images;

}
