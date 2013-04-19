package edu.metcs683.walkabout.dao;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Data access object for the model data.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class AppSettingsDAO {

	private static final String PREFERENCES_NAME = "edu.metcs683.walkabout";

	private static final String PREFERENCES_WAYPOINT_ORDER_ASCENDING_FLAG = "edu.metcs683.walkabout.waypointorderascendingflag";
	private static final boolean PREFERENCES_WAYPOINT_ORDER_ASCENDING_FLAG_DEFAULT = true;

	private static final String PREFERENCES_WAYPOINT_PHOTO_ORDER_ASCENDING_FLAG = "edu.metcs683.walkabout.waypointphotoorderascendingflag";
	private static final boolean PREFERENCES_WAYPOINT_PHOTO_ORDER_ASCENDING_FLAG_DEFAULT = true;

	private final SharedPreferences preferences;

	public AppSettingsDAO(Activity activity) {
		preferences = activity.getSharedPreferences(AppSettingsDAO.PREFERENCES_NAME, Context.MODE_PRIVATE);
	}

	/**
	 * Getter for the Waypoint order ascending flag item.
	 * 
	 * @return - The preference1 item.
	 */
	public boolean getWaypointOrderAscendingFlag() {
		return preferences.getBoolean(AppSettingsDAO.PREFERENCES_WAYPOINT_ORDER_ASCENDING_FLAG,
				AppSettingsDAO.PREFERENCES_WAYPOINT_ORDER_ASCENDING_FLAG_DEFAULT);
	}

	/**
	 * Getter for the Waypoint order ascending flag item.
	 * 
	 * @return - The preference1 item.
	 */
	public boolean getWaypointPhotoOrderAscendingFlag() {
		return preferences.getBoolean(AppSettingsDAO.PREFERENCES_WAYPOINT_PHOTO_ORDER_ASCENDING_FLAG,
				AppSettingsDAO.PREFERENCES_WAYPOINT_PHOTO_ORDER_ASCENDING_FLAG_DEFAULT);
	}

	/**
	 * Setter for the Waypoint order ascending flag item.
	 * 
	 * @param preference1
	 */
	public void setWaypointOrderAscendingFlag(boolean setting) {
		final Editor editor = preferences.edit();
		editor.putBoolean(AppSettingsDAO.PREFERENCES_WAYPOINT_ORDER_ASCENDING_FLAG, setting);
		editor.commit();
		return;
	}

	/**
	 * Setter for the Waypoint order ascending flag item.
	 * 
	 * @param preference1
	 */
	public void setWaypointPhotoOrderAscendingFlag(boolean setting) {
		final Editor editor = preferences.edit();
		editor.putBoolean(AppSettingsDAO.PREFERENCES_WAYPOINT_PHOTO_ORDER_ASCENDING_FLAG, setting);
		editor.commit();
		return;
	}
}
