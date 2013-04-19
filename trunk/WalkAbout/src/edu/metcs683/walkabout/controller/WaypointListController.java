package edu.metcs683.walkabout.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import edu.metcs683.walkabout.dao.AppSettingsDAO;
import edu.metcs683.walkabout.dao.WaypointDAO;
import edu.metcs683.walkabout.model.Waypoint;

/**
 * Controller for the waypoint list functionality.
 * 
 * @author ryszardkilarski
 * 
 */
public class WaypointListController {

	private final AppSettingsDAO appSettingsDAO;
	private final WaypointDAO waypointDAO;

	public WaypointListController(Context context, Activity activity) {
		this.waypointDAO = new WaypointDAO(context);
		this.appSettingsDAO = new AppSettingsDAO(activity);
	}

	public void changeSortOrder() {
		final boolean order = !appSettingsDAO.getWaypointOrderAscendingFlag();
		appSettingsDAO.setWaypointOrderAscendingFlag(order);
	}

	public List<Waypoint> getWaypoints() {
		final boolean orderByAscending = appSettingsDAO.getWaypointOrderAscendingFlag();
		return waypointDAO.getAll(orderByAscending);
	}
}
