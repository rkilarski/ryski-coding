package edu.metcs683.walkabout.controller;

import java.util.List;

import android.content.Context;
import edu.metcs683.walkabout.dao.ImageDAO;
import edu.metcs683.walkabout.dao.WaypointDAO;
import edu.metcs683.walkabout.model.Image;
import edu.metcs683.walkabout.model.Waypoint;

/**
 * Controller for the waypoint mapping functionality.
 * 
 * @author ryszardkilarski
 * 
 */
public class WaypointMapController {
	private final WaypointDAO waypointDAO;
	private final ImageDAO imageDAO;

	public WaypointMapController(Context context) {
		this.waypointDAO = new WaypointDAO(context);
		this.imageDAO = new ImageDAO(context);
	}

	public Waypoint getWaypointById(long id) {
		return waypointDAO.get(id);
	}

	public List<Image> getImageList(long id) {
		return imageDAO.getAll(true, id);
	}

	public String getWaypointDescription(long id) {
		final Waypoint waypoint = waypointDAO.get(id);
		return waypoint.getDescription();
	}
}
