package edu.metcs683.walkabout;

import java.util.List;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import edu.metcs683.walkabout.controller.WaypointMapController;
import edu.metcs683.walkabout.model.Image;
import edu.metcs683.walkabout.model.Waypoint;
import android.os.Bundle;
import android.content.Intent;

/**
 * Main user interface for the waypoint map.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class WaypointMap extends MapActivity {

	private WaypointMapController controller;

	// private MapController mapController;
	// private MapView mapView;

	/**
	 * Initialize any controllers.
	 */
	private void initializeControllers() {
		controller = new WaypointMapController(this);
	}

	/**
	 * Initialize the UI objects and attach any handlers.
	 */
	private void initializeUI() {
		// mapView = (MapView) findViewById(R.id.waypointMap);
		// mapView.setBuiltInZoomControls(true);
	}

	/**
	 * Load the data into the form.
	 */
	private void loadData() {
		final Intent intent = getIntent();
		final long id = intent.getLongExtra("waypointId", 0);
		final List<Image> list = controller.getImageList(id);
		final Waypoint waypoint = controller.getWaypointById(id);

		setTitle(controller.getWaypointDescription(id) + " Map");

		// Set the map to be around the waypoint location.
		GeoPoint lastKnownPoint = new GeoPoint(((int) (waypoint.getLatitude() * 1E6)),
				((int) (waypoint.getLongitude() * 1E6)));
		// mapController = mapView.getController();
		// mapController.setZoom(10);
		// mapController.animateTo(lastKnownPoint);

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waypoint_map);
		int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (result == (ConnectionResult.SERVICE_MISSING)
				|| (result == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED)
				|| (result == ConnectionResult.SERVICE_MISSING)) {
			GooglePlayServicesUtil.getErrorDialog(result, this, 0);
		}
		initializeUI();
		initializeControllers();
		loadData();
	}
}
