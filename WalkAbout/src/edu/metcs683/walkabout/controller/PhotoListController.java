package edu.metcs683.walkabout.controller;

import java.util.List;

import android.content.Context;
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

	public PhotoListController(Context context) {
		imageDAO = new ImageDAO(context);
	}

	public List<Image> getImageList(long id) {
		return imageDAO.getAll(id);
	}
}
