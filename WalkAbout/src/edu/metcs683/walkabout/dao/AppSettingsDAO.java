package edu.metcs683.walkabout.dao;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import edu.metcs683.walkabout.model.AppSettings;

public class AppSettingsDAO implements Database<AppSettings> {

	private static final String PREFERENCES_NAME = "edu.metcs683.walkabout";
	private SharedPreferences preferences;
	
	public AppSettingsDAO(Activity activity){
		preferences = activity.getSharedPreferences(AppSettingsDAO.PREFERENCES_NAME, Context.MODE_PRIVATE);
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
