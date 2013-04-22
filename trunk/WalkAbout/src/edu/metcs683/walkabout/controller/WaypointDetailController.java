package edu.metcs683.walkabout.controller;

import android.content.Context;
import edu.metcs683.walkabout.dao.WaypointDAO;
import edu.metcs683.walkabout.model.Waypoint;

/**
 * Controller for the waypoint detail functionality.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
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

	public long saveWaypoint(Waypoint waypoint) {
		long id = 0;
		if (waypoint.getId() == 0) {
			id = waypointDAO.insert(waypoint);
		} else {
			waypointDAO.update(waypoint);
			id = waypoint.getId();
		}
		return id;
	}
}
