package edu.metcs683.walkabout.controller;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import edu.metcs683.walkabout.dao.AppSettingsDAO;
import edu.metcs683.walkabout.dao.ImageDAO;
import edu.metcs683.walkabout.dao.WaypointDAO;
import edu.metcs683.walkabout.model.Image;
import edu.metcs683.walkabout.model.Waypoint;

/**
 * Controller for the photo list.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class PhotoListController {

	private final AppSettingsDAO appSettingsDAO;
	private final ImageDAO imageDAO;
	private final WaypointDAO waypointDAO;

	public PhotoListController(Context context, Activity activity) {
		this.imageDAO = new ImageDAO(context);
		this.waypointDAO = new WaypointDAO(context);
		this.appSettingsDAO = new AppSettingsDAO(activity);
	}

	public void changeSortOrder() {
		final boolean order = !appSettingsDAO.getWaypointPhotoOrderAscendingFlag();
		appSettingsDAO.setWaypointPhotoOrderAscendingFlag(order);
	}

	public void deleteImage(long id) {
		imageDAO.delete(id);
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

	public List<Image> getImageList(long id) {
		final boolean orderByAscending = appSettingsDAO.getWaypointPhotoOrderAscendingFlag();
		return imageDAO.getAll(orderByAscending, id);
	}

	public String getWaypointDescription(long id) {
		final Waypoint waypoint = waypointDAO.get(id);
		return waypoint.getDescription();
	}

	public void saveImage(Image image) {
		imageDAO.insert(image);
	}

}
