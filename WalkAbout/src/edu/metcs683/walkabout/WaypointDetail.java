package edu.metcs683.walkabout;

import java.util.Calendar;
import java.util.Date;

import edu.metcs683.walkabout.controller.WaypointDetailController;
import edu.metcs683.walkabout.model.Waypoint;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * User interface for the Waypoint detail screen.
 * 
 * @author ryszardkilarski
 * 
 */
public class WaypointDetail extends Activity {

	public static final int ADD_WAYPOINT = 2;
	public static final int EDIT_WAYPOINT = 1;
	private Button cancelButton;
	private WaypointDetailController controller;
	private EditText description;
	private Button okButton;
	private Waypoint waypoint;
	private DatePicker waypointDate;
	private Button locationButton;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_waypoint_detail, menu);
		return true;
	}

	/**
	 * Initialize the UI objects and attach any handlers.
	 */
	private void initializeUI() {
		setContentView(R.layout.activity_waypoint_detail);
		// Attach to UI elements
		description = (EditText) this.findViewById(R.id.descriptionField);
		waypointDate = (DatePicker) this.findViewById(R.id.dateCreated);

		cancelButton = (Button) this.findViewById(R.id.cancelButton);
		okButton = (Button) this.findViewById(R.id.okButton);
		locationButton = (Button) this.findViewById(R.id.locationButton);

		// Attach handlers
		cancelButton.setOnClickListener(new CancelButtonListener());
		okButton.setOnClickListener(new OKButtonListener());
		locationButton.setOnClickListener(new LocationButtonListener());
	}

	/**
	 * Get data from intent and load into the form.
	 */
	private void loadData() {
		Intent intent = this.getIntent();
		long id = intent.getLongExtra("waypointId", 0);
		if (id > 0) {
			waypoint = controller.getWaypointById(id);
			mapWaypointToForm(waypoint);
		} else {
			Location location = getLocation();
			waypoint = new Waypoint(0, null, new Date(), false, location.toString());
			mapWaypointToForm(waypoint);
		}
	}

	/**
	 * Put the waypoint form data into a waypoint object.
	 * 
	 * @return
	 */
	private Waypoint mapFormToWaypoint() {
		waypoint.setDescription(description.getText().toString());
		waypoint.setDateTime(getDateFromWaypointDate());
		// TODO
		return waypoint;
	}

	/**
	 * Convert a datepicker date into a date object.
	 * 
	 * @return
	 */
	private Date getDateFromWaypointDate() {
		int day = waypointDate.getDayOfMonth();
		int month = waypointDate.getMonth();
		int year = waypointDate.getYear();

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);

		return calendar.getTime();
	}

	/**
	 * Put the waypoint data into the form.
	 * 
	 * @param waypoint
	 */
	private void mapWaypointToForm(Waypoint waypoint) {
		description.setText(waypoint.getDescription());

		Calendar c = Calendar.getInstance();
		c.setTime(waypoint.getDateTime());
		waypointDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH), null);

		locationButton.setText("Location: " + waypoint.getLocation());
		// TODO
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeUI();
		controller = new WaypointDetailController(getApplicationContext());
		loadData();
	}

	/**
	 * Handler for the Cancel button.
	 */
	private class CancelButtonListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			finish();
		}
	}

	/**
	 * Handler for the Cancel button.
	 */
	private class LocationButtonListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			Location location = getLocation();
			if (location != null) {
				waypoint.setLocation(location.toString());
				locationButton.setText(getString(R.string.locationButton) + " "
						+ location.toString());
			} else {
				locationButton
						.setText(getString(R.string.locationButtonUnknown));
			}
		}
	}

	/**
	 * Handler for the OK button.
	 */
	private class OKButtonListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			Waypoint waypoint = mapFormToWaypoint();
			controller.saveWaypoint(waypoint);

			// Communicate back to the caller the new waypoint id.
			Intent intent = new Intent();
			intent.putExtra("waypointId", waypoint.getId());
			setResult(Activity.RESULT_OK, intent);

			Toast.makeText(
					getApplicationContext(),
					"Waypoint " + waypoint.getDescription()
							+ " has been filed.", Toast.LENGTH_LONG).show();
			finish();
		}
	}

	private Location getLocation() {
		Location location = null;
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		boolean enabled = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (enabled) {
			Criteria criteria = new Criteria();
			String provider = locationManager.getBestProvider(criteria, false);
			location = locationManager.getLastKnownLocation(provider);
			if (location == null) {
				// What do we do?
				location = new Location("network"); // Try returning network
													// location.
			}
		}
		return location;
	}
}
