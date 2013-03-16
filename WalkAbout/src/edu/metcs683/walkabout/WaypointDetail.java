package edu.metcs683.walkabout;

import edu.metcs683.walkabout.controller.WaypointDetailController;
import edu.metcs683.walkabout.model.Waypoint;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_waypoint_detail, menu);
		return true;
	}

	private void initializeUI() {
		setContentView(R.layout.activity_waypoint_detail);
		// Attach to UI elements
		description = (EditText) this.findViewById(R.id.descriptionField);
		cancelButton = (Button) this.findViewById(R.id.cancelButton);
		okButton = (Button) this.findViewById(R.id.okButton);

		// Attach handlers
		cancelButton.setOnClickListener(new CancelButtonListener());
		okButton.setOnClickListener(new OKButtonListener());
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
			waypoint = new Waypoint(0, null, null, false, null);
		}
	}

	private Waypoint mapFormToWaypoint() {
		waypoint.setDescription(description.getText().toString());
		//TODO
		return waypoint;
	}

	private void mapWaypointToForm(Waypoint waypoint) {
		description.setText(waypoint.getDescription());
		//TODO
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

			finish();
		}
	}
}
