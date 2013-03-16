package edu.metcs683.walkabout.controller;

import android.content.Context;
import edu.metcs683.walkabout.dao.WaypointDAO;
import edu.metcs683.walkabout.model.Waypoint;

public class WaypointDetailController {
	private WaypointDAO waypointDAO;

	public WaypointDetailController(Context context) {
		waypointDAO = new WaypointDAO(context);
	}

	public Waypoint getWaypointById(long id) {
		return waypointDAO.get(id);
	}

	public void saveWaypoint(Waypoint waypoint) {
		if (waypoint.getId() == 0) {
			waypointDAO.insert(waypoint);
		} else {
			waypointDAO.update(waypoint);
		}
	}
}
