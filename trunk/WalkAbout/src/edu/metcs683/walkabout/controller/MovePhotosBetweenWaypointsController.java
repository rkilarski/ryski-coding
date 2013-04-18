package edu.metcs683.walkabout.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;

import edu.metcs683.walkabout.dao.AppSettingsDAO;
import edu.metcs683.walkabout.dao.ImageDAO;
import edu.metcs683.walkabout.dao.WaypointDAO;
import edu.metcs683.walkabout.model.Image;
import edu.metcs683.walkabout.model.Waypoint;

/**
 * Controller for moving photos between waypoints.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class MovePhotosBetweenWaypointsController {
	private AppSettingsDAO appSettingsDAO;
	private ImageDAO imageDAO;
	private WaypointDAO waypointDAO;

	/**
	 * Constructor for this controller.
	 * 
	 * @param context
	 * @param activity
	 */
	public MovePhotosBetweenWaypointsController(Context context,
			Activity activity) {
		waypointDAO = new WaypointDAO(context);
		imageDAO = new ImageDAO(context);
		appSettingsDAO = new AppSettingsDAO(activity);
	}

	/**
	 * Get list of images.
	 * 
	 * @param id
	 * @return
	 */
	public List<Image> getImageList(long id) {
		boolean orderByAscending = appSettingsDAO
				.getWaypointPhotoOrderAscendingFlag();
		return imageDAO.getAll(orderByAscending, id);
	}

	/**
	 * Get list of waypoints.
	 * 
	 * @return
	 */
	public List<Waypoint> getWaypoints() {
		boolean orderByAscending = appSettingsDAO
				.getWaypointOrderAscendingFlag();
		return waypointDAO.getAll(orderByAscending);
	}

	/**
	 * Move list of images from their current waypoint to the target waypoint.
	 * 
	 * @param toWaypoint
	 * @param images
	 */
	public void movePhotos(long toWaypoint, List<Image> images) {
		for (Image image : images) {
			image.setWaypointId(toWaypoint);
			imageDAO.update(image);
		}
	}
}
