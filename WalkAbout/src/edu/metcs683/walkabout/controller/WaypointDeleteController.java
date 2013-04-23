package edu.metcs683.walkabout.controller;

import android.app.Activity;
import android.content.Context;
import edu.metcs683.walkabout.dao.ImageDAO;
import edu.metcs683.walkabout.dao.WaypointDAO;

/**
 * Controller for the photo list.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class WaypointDeleteController {

	private final ImageDAO imageDAO;
	private final WaypointDAO waypointDAO;

	public WaypointDeleteController(Context context, Activity activity) {
		this.imageDAO = new ImageDAO(context);
		this.waypointDAO = new WaypointDAO(context);
	}

	/**
	 * Delete the waypoint and all its images.
	 * 
	 * @param id
	 */
	public void deleteWaypoint(long id) {
		waypointDAO.delete(id);
		imageDAO.deleteAll(id);
	}
}
