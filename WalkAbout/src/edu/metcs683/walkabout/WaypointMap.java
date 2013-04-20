package edu.metcs683.walkabout;

import java.util.List;

import edu.metcs683.walkabout.controller.WaypointMapController;
import edu.metcs683.walkabout.model.Image;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class WaypointMap extends Activity {

	private WaypointMapController controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.activity_waypoint_map);
		} catch (Exception exception) {
			;
		}
		initializeUI();
		initializeControllers();
		loadData();
	}

	private void initializeUI() {

	}

	/**
	 * Load the data into the form.
	 */
	private void loadData() {
		final Intent intent = getIntent();
		final long id = intent.getLongExtra("waypointId", 0);
		final List<Image> list = controller.getImageList(id);

	}

	/**
	 * Initialize any controllers.
	 */
	private void initializeControllers() {
		controller = new WaypointMapController(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.waypoint_map, menu);
		return true;
	}

}
