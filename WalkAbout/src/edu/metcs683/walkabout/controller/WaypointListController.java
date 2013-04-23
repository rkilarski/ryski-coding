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
 * Controller for the waypoint list functionality.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class WaypointListController {

	private final AppSettingsDAO appSettingsDAO;
	private final WaypointDAO waypointDAO;
	private final ImageDAO imageDAO;
	
	public WaypointListController(Context context, Activity activity) {
		this.waypointDAO = new WaypointDAO(context);
		this.appSettingsDAO = new AppSettingsDAO(activity);
		this.imageDAO = new ImageDAO(context);
	}

	public void changeSortOrder() {
		final boolean order = !appSettingsDAO.getWaypointOrderAscendingFlag();
		appSettingsDAO.setWaypointOrderAscendingFlag(order);
	}

	public List<Waypoint> getWaypoints() {
		final boolean orderByAscending = appSettingsDAO.getWaypointOrderAscendingFlag();
		return waypointDAO.getAll(orderByAscending);
	}

	public boolean getWaypointOrder() {
		return appSettingsDAO.getWaypointOrderAscendingFlag();
	}
	
	public void saveImage(Image image) {
		imageDAO.insert(image);
	}

}
