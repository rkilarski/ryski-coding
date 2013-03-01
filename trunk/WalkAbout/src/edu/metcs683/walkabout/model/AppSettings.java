package edu.metcs683.walkabout.model;

import android.app.Activity;
import edu.metcs683.walkabout.dao.AppSettingsDAO;

public class AppSettings {

	private AppSettingsDAO appSettingsDAO = null;

	public AppSettings(Activity activity) {
		appSettingsDAO = new AppSettingsDAO(activity);

	}

	public boolean getWaypointOrderAscending() {
		return false;
	}

	public boolean getWaypointPhotoOrderAscending() {
		return false;
	}
}
