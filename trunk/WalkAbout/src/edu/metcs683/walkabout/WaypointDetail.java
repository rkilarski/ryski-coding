package edu.metcs683.walkabout;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import edu.metcs683.walkabout.controller.WaypointDetailController;
import edu.metcs683.walkabout.model.Waypoint;
import edu.metcs683.walkabout.uihelper.LocationService;

/**
 * User interface for the Waypoint detail screen.
 * 
 * @author ryszardkilarski
 * 
 */
public class WaypointDetail extends Activity {

	public static final int ADD_WAYPOINT = 2;
	public static final int EDIT_WAYPOINT = 1;
	public static final int DELETE_WAYPOINT = 3;
	public static final int REORDER_WAYPOINT = 4;
	public static final int MOVE_PHOTOS = 5;
	private Button cancelButton;
	private WaypointDetailController controller;
	private EditText description;
	private Button locationButton;
	private Button okButton;
	private Waypoint waypoint;
	private DatePicker waypointDate;

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_waypoint_detail, menu);
		return true;
	}

	/**
	 * Convert a datepicker date into a date object.
	 * 
	 * @return
	 */
	private Date getDateFromWaypointDate() {
		final int day = waypointDate.getDayOfMonth();
		final int month = waypointDate.getMonth();
		final int year = waypointDate.getYear();

		final Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);

		return calendar.getTime();
	}

	/**
	 * Initialize the UI objects and attach any handlers.
	 */
	private void initializeUI() {
		setContentView(R.layout.activity_waypoint_detail);
		// Attach to UI elements
		description = (EditText) findViewById(R.id.descriptionField);
		waypointDate = (DatePicker) findViewById(R.id.dateCreated);

		cancelButton = (Button) findViewById(R.id.cancelButton);
		okButton = (Button) findViewById(R.id.okButton);
		locationButton = (Button) findViewById(R.id.locationButton);

		// Attach handlers
		cancelButton.setOnClickListener(new CancelButtonListener());
		okButton.setOnClickListener(new OKButtonListener());
		locationButton.setOnClickListener(new LocationButtonListener());
	}

	/**
	 * Get data from intent and load into the form.
	 */
	private void loadData() {
		final Intent intent = getIntent();
		final long id = intent.getLongExtra("waypointId", 0);
		if (id > 0) {
			waypoint = controller.getWaypointById(id);
			mapWaypointToForm(waypoint);
		} else {
			final Location location = LocationService.getLocation(this);
			waypoint = new Waypoint(0, null, new Date(), false, location.getLatitude(), location.getLongitude());
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
	 * Put the waypoint data into the form.
	 * 
	 * @param waypoint
	 */
	private void mapWaypointToForm(Waypoint waypoint) {
		description.setText(waypoint.getDescription());

		final Calendar c = Calendar.getInstance();
		c.setTime(waypoint.getDateTime());
		waypointDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), null);

		locationButton.setText(waypoint.getLatitude() + "(lat) " + waypoint.getLongitude() + "(long)");
		// TODO
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeUI();
		initializeControllers();
		loadData();
	}

	/**
	 * Set up any needed controllers
	 */
	private void initializeControllers() {
		controller = new WaypointDetailController(getApplicationContext());
	}

	/**
	 * Handler for the Cancel button.
	 */
	private class CancelButtonListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			finish();
			overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
		}
	}

	/**
	 * Handler for the Cancel button.
	 */
	private class LocationButtonListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			final Location location = LocationService.getLocation(WaypointDetail.this);
			if (location != null) {
				waypoint.setLatitude(location.getLatitude());
				waypoint.setLongitude(location.getLongitude());
				locationButton.setText(waypoint.getLatitude() + "(lat) " + waypoint.getLongitude() + "(long)");
			} else {
				locationButton.setText(getString(R.string.locationButtonUnknown));
			}
		}
	}

	/**
	 * Handler for the OK button.
	 */
	private class OKButtonListener implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			final Waypoint waypoint = mapFormToWaypoint();
			long id = controller.saveWaypoint(waypoint);

			// Communicate back to the caller the new waypoint id.
			final Intent intent = new Intent();
			intent.putExtra("waypointId", id);
			setResult(Activity.RESULT_OK, intent);

			Toast.makeText(getApplicationContext(), "Waypoint " + waypoint.getDescription() + " has been filed.",
					Toast.LENGTH_LONG).show();
			finish();
			overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
		}
	}
}
