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
public class WaypointPhotoDeleteController {
	private final AppSettingsDAO appSettingsDAO;
	private final ImageDAO imageDAO;
	private final WaypointDAO waypointDAO;

	/**
	 * Constructor for this controller.
	 * 
	 * @param context
	 * @param activity
	 */
	public WaypointPhotoDeleteController(Context context, Activity activity) {
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

	public String getWaypointDescription(long id) {
		final Waypoint waypoint = waypointDAO.get(id);
		return waypoint.getDescription();
	}

	public void deleteImage(long id) {
		imageDAO.delete(id);
	}

}
