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
 * Controller for the photo list.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class PhotoListController {

	private WaypointDAO waypointDAO;
	private ImageDAO imageDAO;
	private AppSettingsDAO appSettingsDAO;

	public PhotoListController(Context context, Activity activity) {
		imageDAO = new ImageDAO(context);
		waypointDAO = new WaypointDAO(context);
		appSettingsDAO = new AppSettingsDAO(activity);
	}

	public List<Image> getImageList(long id) {
		boolean orderByAscending = appSettingsDAO
				.getWaypointPhotoOrderAscendingFlag();
		return imageDAO.getAll(orderByAscending, id);
	}

	public String getWaypointDescription(long id) {
		Waypoint waypoint = waypointDAO.get(id);
		return waypoint.getDescription();
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
	
	public void saveImage(Image image){
		imageDAO.insert(image);
	}
}
