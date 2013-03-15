package edu.metcs683.walkabout.controller;

import java.util.List;

import android.content.Context;

import edu.metcs683.walkabout.dao.WaypointDAO;
import edu.metcs683.walkabout.model.Waypoint;

public class WaypointListController {

	private List<Waypoint> waypoints;
	private WaypointDAO waypointDAO;

	public WaypointListController(Context context) {
		waypointDAO = new WaypointDAO(context);
	}

}
