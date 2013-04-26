package edu.metcs683.walkabout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import edu.metcs683.walkabout.controller.MovePhotosBetweenWaypointsController;
import edu.metcs683.walkabout.model.Image;
import edu.metcs683.walkabout.model.Waypoint;
import edu.metcs683.walkabout.uihelper.ErrorDisplay;
import edu.metcs683.walkabout.uihelper.ImageAdapter;

/**
 * User interface for the move images between waypoints functionality.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class WaypointPhotoMove extends Activity {

	private Button cancelButton;
	private MovePhotosBetweenWaypointsController controller;
	private SimpleAdapter listViewAdapter;
	private Spinner targetWaypoint;
	private Button okButton;
	private GridView photoList;
	@SuppressLint("UseSparseArrays")
	private final Map<Integer, Image> selectedPhotos = new HashMap<Integer, Image>();

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_waypoint_photo_move, menu);
		return true;
	}

	/**
	 * Load the data into the UI.
	 */
	private void initializeUI() {
		setContentView(R.layout.activity_waypoint_photo_move);
		// Attach to UI elements
		photoList = (GridView) findViewById(R.id.photoList);
		targetWaypoint = (Spinner) findViewById(R.id.moveToWaypoint);

		cancelButton = (Button) findViewById(R.id.cancelButton);
		okButton = (Button) findViewById(R.id.okButton);

		// Attach handlers
		cancelButton.setOnClickListener(new CancelButtonHandler());
		okButton.setOnClickListener(new OKButtonHandler());
		photoList.setOnItemClickListener(new ImageClickHandler());
		targetWaypoint.setOnItemSelectedListener(new SpinnerClickHandler());

	}

	/**
	 * Get data from intent and load into the form.
	 */
	private void loadData() {
		try {
			final Intent intent = getIntent();
			final long sourceWaypointId = intent.getLongExtra("waypointId", 0);

			// Load waypoint list into spinner.
			final List<Waypoint> waypoints = controller.getWaypoints();
			final List<Map<String, String>> data = new ArrayList<Map<String, String>>();
			for (final Waypoint waypoint : waypoints) {
				// Exclude the source waypoint.
				if (waypoint.getId() != sourceWaypointId) {
					final Map<String, String> datum = new HashMap<String, String>(
							2);
					datum.put("description", waypoint.getDescription());
					datum.put("date", waypoint.getDateTime().toString());
					datum.put("id", Long.toString(waypoint.getId()));
					data.add(datum);
				}
			}
			listViewAdapter = new SimpleAdapter(this, data,
					android.R.layout.simple_list_item_2, new String[] {
							"description", "date" }, new int[] {
							android.R.id.text1, android.R.id.text2 });
			targetWaypoint.setAdapter(listViewAdapter);

			final List<Image> list = controller.getImageList(sourceWaypointId);
			photoList.setAdapter(new ImageAdapter(this, list));
		} catch (Exception ex) {
			Context context = getApplicationContext();
			ErrorDisplay.displayMessage(this, context,
					context.getString(R.string.error_message_load_data), ex);
		}
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
		controller = new MovePhotosBetweenWaypointsController(
				getApplicationContext(), this);
	}

	/**
	 * Handler for the Cancel button.
	 */
	private class CancelButtonHandler implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			finish();
			overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
		}
	}

	/**
	 * Click handler for when selecting/unselecting an image in the list.
	 */
	private class ImageClickHandler implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			// Get the image from the adapter...
			final Image image = (Image) parent.getAdapter().getItem(position);

			// And change the cell color to white or cyan, if selected.
			if (selectedPhotos.containsKey(position)) {
				selectedPhotos.remove(position);
				View view = photoList.getChildAt(position);
				view.setBackgroundColor(Color.WHITE);
			} else {
				selectedPhotos.put(position, image);
				View view = photoList.getChildAt(position);
				view.setBackgroundColor(Color.CYAN);

			}
		}
	}

	/**
	 * Handler for the OK button.
	 */
	private class OKButtonHandler implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			try {
				final List<Image> images = new ArrayList<Image>();
				// Get the list of images to move.
				for (Image image : selectedPhotos.values()) {
					images.add(image);
				}
				// Get target waypoint.
				int selectedItemPosition = targetWaypoint
						.getSelectedItemPosition();
				@SuppressWarnings("unchecked")
				final Map<String, String> row = (HashMap<String, String>) listViewAdapter
						.getItem(selectedItemPosition);
				long targetWaypoint = Long.parseLong(row.get("id"));

				controller.movePhotos(targetWaypoint, images);

				final Intent intent = getIntent();
				final long sourceWaypoint = intent
						.getLongExtra("waypointId", 0);
				final Intent returnIntent = new Intent();

				returnIntent.putExtra("waypointId", targetWaypoint);
				returnIntent.putExtra("waypointId2", sourceWaypoint);
				setResult(Activity.RESULT_OK, returnIntent);
			} catch (Exception ex) {
				Context context = getApplicationContext();
				ErrorDisplay.displayMessage(WaypointPhotoMove.this, context,
						context.getString(R.string.error_message_move_photos),
						ex);
			}

			finish();
			overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
		}
	}

	/**
	 * Handler for the waypoint selector.
	 * 
	 */
	private class SpinnerClickHandler implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			okButton.setEnabled(true);
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			okButton.setEnabled(false);
		}

	}
}
