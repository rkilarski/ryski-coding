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

	public AppSettings(Activity activity) {
		appSettings = new AppSettingsDAO(activity);

	}

	public boolean getWaypointOrderAscendingFlag() {
		return appSettings.getWaypointOrderAscendingFlag();
	}

	public boolean getWaypointPhotoOrderAscendingFlag() {
		return appSettings.getWaypointPhotoOrderAscendingFlag();
	}

	public void setWaypointOrderAscendingFlag(boolean setting) {
		appSettings.setWaypointOrderAscendingFlag(setting);
		return;
	}

	public void setWaypointPhotoOrderAscendingFlag(boolean setting) {
		appSettings.setWaypointPhotoOrderAscendingFlag(setting);
		return;
	}
}
