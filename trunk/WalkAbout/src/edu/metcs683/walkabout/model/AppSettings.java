package edu.metcs683.walkabout.model;

import android.app.Activity;
import edu.metcs683.walkabout.dao.AppSettingsDAO;

/**
 * Model class for the App Settings data.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class AppSettings {

	private AppSettingsDAO appSettings = null;

	/**
	 * Constructor for this class.
	 * 
	 * @param activity
	 */
	public AppSettings(Activity activity) {
		appSettings = new AppSettingsDAO(activity);

	}

	/**
	 * Get the waypoint order flag.
	 * 
	 * @return
	 */
	public boolean getWaypointOrderAscendingFlag() {
		return appSettings.getWaypointOrderAscendingFlag();
	}

	/**
	 * Get the waypoint photo order flag.
	 * 
	 * @return
	 */
	public boolean getWaypointPhotoOrderAscendingFlag() {
		return appSettings.getWaypointPhotoOrderAscendingFlag();
	}

	/**
	 * Set the waypoint order flag.
	 * @param setting
	 */
	public void setWaypointOrderAscendingFlag(boolean setting) {
		appSettings.setWaypointOrderAscendingFlag(setting);
		return;
	}

	/**
	 * Set the waypoint photo order flag.
	 * @param setting
	 */
	public void setWaypointPhotoOrderAscendingFlag(boolean setting) {
		appSettings.setWaypointPhotoOrderAscendingFlag(setting);
		return;
	}
}
