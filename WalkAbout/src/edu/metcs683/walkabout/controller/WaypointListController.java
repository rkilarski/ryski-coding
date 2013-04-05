package edu.metcs683.walkabout.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;

import edu.metcs683.walkabout.dao.AppSettingsDAO;
import edu.metcs683.walkabout.dao.WaypointDAO;
import edu.metcs683.walkabout.model.Waypoint;

public class WaypointListController {

	private List<Waypoint> waypoints;
	private WaypointDAO waypointDAO;
	private AppSettingsDAO appSettingsDAO;

	public WaypointListController(Context context, Activity activity) {
		waypointDAO = new WaypointDAO(context);
		appSettingsDAO = new AppSettingsDAO(activity);
	}

	public List<Waypoint> getWaypoints() {
		boolean orderByAscending = appSettingsDAO
				.getWaypointOrderAscendingFlag();
		return waypointDAO.getAll(orderByAscending);
	}
}
