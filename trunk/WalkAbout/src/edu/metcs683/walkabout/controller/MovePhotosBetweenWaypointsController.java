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
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class MovePhotosBetweenWaypointsController {
	private final AppSettingsDAO appSettingsDAO;
	private final ImageDAO imageDAO;
	private final WaypointDAO waypointDAO;

	/**
	 * Constructor for this controller.
	 * 
	 * @param context
	 * @param activity
	 */
	public MovePhotosBetweenWaypointsController(Context context, Activity activity) {
		this.waypointDAO = new WaypointDAO(context);
		this.imageDAO = new ImageDAO(context);
		this.appSettingsDAO = new AppSettingsDAO(activity);
	}

	/**
	 * Get list of images.
	 * 
	 * @param id
	 * @return
	 */
	public List<Image> getImageList(long id) {
		final boolean orderByAscending = appSettingsDAO.getWaypointPhotoOrderAscendingFlag();
		return imageDAO.getAll(orderByAscending, id);
	}

	/**
	 * Get list of waypoints.
	 * 
	 * @return
	 */
	public List<Waypoint> getWaypoints() {
		final boolean orderByAscending = appSettingsDAO.getWaypointOrderAscendingFlag();
		return waypointDAO.getAll(orderByAscending);
	}

	/**
	 * Move list of images from their current waypoint to the target waypoint.
	 * 
	 * @param toWaypoint
	 * @param images
	 */
	public void movePhotos(long toWaypoint, List<Image> images) {
		for (final Image image : images) {
			image.setWaypointId(toWaypoint);
			imageDAO.update(image);
		}
	}
}
