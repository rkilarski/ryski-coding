package edu.metcs683.walkabout.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;

import edu.metcs683.walkabout.dao.AppSettingsDAO;
import edu.metcs683.walkabout.dao.WaypointDAO;
import edu.metcs683.walkabout.model.Waypoint;

public class WaypointListController {

	private AppSettingsDAO appSettingsDAO;
	private WaypointDAO waypointDAO;

	public WaypointListController(Context context, Activity activity) {
		waypointDAO = new WaypointDAO(context);
		appSettingsDAO = new AppSettingsDAO(activity);
	}

	public void changeSortOrder() {
		boolean order = !appSettingsDAO.getWaypointOrderAscendingFlag();
		appSettingsDAO.setWaypointOrderAscendingFlag(order);
	}

	public List<Waypoint> getWaypoints() {
		boolean orderByAscending = appSettingsDAO
				.getWaypointOrderAscendingFlag();
		return waypointDAO.getAll(orderByAscending);
	}
}
