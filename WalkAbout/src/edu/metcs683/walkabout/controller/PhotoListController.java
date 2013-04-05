package edu.metcs683.walkabout.controller;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import edu.metcs683.walkabout.dao.AppSettingsDAO;
import edu.metcs683.walkabout.dao.ImageDAO;
import edu.metcs683.walkabout.model.Image;

/**
 * Controller for the photo list.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class PhotoListController {

	private ImageDAO imageDAO;
	private AppSettingsDAO appSettingsDAO;

	public PhotoListController(Context context, Activity activity) {
		imageDAO = new ImageDAO(context);
		appSettingsDAO = new AppSettingsDAO(activity);
	}

	public List<Image> getImageList(long id) {
		boolean orderByAscending = appSettingsDAO
				.getWaypointPhotoOrderAscendingFlag();
		return imageDAO.getAll(orderByAscending, id);
	}
}
