package edu.metcs683.walkabout.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import edu.metcs683.walkabout.dao.AppSettingsDAO;
import edu.metcs683.walkabout.dao.ImageDAO;
import edu.metcs683.walkabout.dao.WaypointDAO;
import edu.metcs683.walkabout.model.Image;
import edu.metcs683.walkabout.model.Waypoint;

/**
 * Controller for the waypoint view.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class WaypointViewController {

	private final AppSettingsDAO appSettingsDAO;
	private final ImageDAO imageDAO;
	private final WaypointDAO waypointDAO;

	public WaypointViewController(Context context, Activity activity) {
		this.imageDAO = new ImageDAO(context);
		this.waypointDAO = new WaypointDAO(context);
		this.appSettingsDAO = new AppSettingsDAO(activity);
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
		final boolean orderByAscending = appSettingsDAO
				.getWaypointPhotoOrderAscendingFlag();
		return imageDAO.getAll(orderByAscending, id);
	}

	public String getWaypointDescription(long id) {
		final Waypoint waypoint = waypointDAO.get(id);
		return waypoint.getDescription();
	}

	public boolean isWaypointExpanded(long id){
		final Waypoint waypoint = waypointDAO.get(id);
		return waypoint.isExpanded();
	}
	
	public void expandCollapseWaypoint(long id){
		final Waypoint waypoint = waypointDAO.get(id);
		waypoint.setExpanded(!waypoint.isExpanded());
		waypointDAO.update(waypoint);
	}
	
	@SuppressLint("SimpleDateFormat")
	public String getWaypointDate(long id) {
		final Waypoint waypoint = waypointDAO.get(id);
		Date date = waypoint.getDateTime();
		DateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy");

		return format.format(date);
	}

	public void saveImage(Image image) {
		imageDAO.insert(image);
	}

}
