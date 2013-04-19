package edu.metcs683.walkabout.controller;

import android.content.Context;
import edu.metcs683.walkabout.dao.WaypointDAO;
import edu.metcs683.walkabout.model.Waypoint;

/**
 * Controller for the waypoint detail functionality.
 * 
 * @author ryszardkilarski
 * 
 */
public class WaypointDetailController {
	private final WaypointDAO waypointDAO;

	public WaypointDetailController(Context context) {
		this.waypointDAO = new WaypointDAO(context);
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
