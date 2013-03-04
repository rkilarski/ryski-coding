package edu.metcs683.walkabout.dao;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import edu.metcs683.walkabout.model.AppSettings;

public class AppSettingsDAO implements Database<AppSettings> {

	private static final String PREFERENCES_NAME = "edu.metcs683.walkabout";
	private static final String PREFERENCES_PREFERENCE1 = "edu.metcs683.walkabout.preference1";
	private static final String PREFERENCES_PREFERENCE1_DEFAULT = "";

	private SharedPreferences preferences;

	public AppSettingsDAO(Activity activity) {
		preferences = activity.getSharedPreferences(
				AppSettingsDAO.PREFERENCES_NAME, Context.MODE_PRIVATE);
	}

	/**
	 * Getter for the Preference1 item.
	 * 
	 * @return - The preference1 item.
	 */
	public String getPreference1() {
		return preferences.getString(AppSettingsDAO.PREFERENCES_PREFERENCE1,
				AppSettingsDAO.PREFERENCES_PREFERENCE1_DEFAULT);
	}

	/**
	 * Setter for the Preference1 item.
	 * 
	 * @param preference1
	 */
	public void setPreference1(String preference1) {
		Editor editor = preferences.edit();
		editor.putString(AppSettingsDAO.PREFERENCES_PREFERENCE1, preference1);
		editor.commit();
		return;
	}

	@Override
	public AppSettings get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(AppSettings object) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AppSettings> getList(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(AppSettings object) {
		// TODO Auto-generated method stub

	}

}
