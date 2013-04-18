package edu.metcs683.walkabout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.metcs683.walkabout.controller.MovePhotosBetweenWaypointsController;
import edu.metcs683.walkabout.model.Image;
import edu.metcs683.walkabout.model.Waypoint;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;

public class MovePhotosBetweenWaypoints extends Activity {

	private Button cancelButton;
	private MovePhotosBetweenWaypointsController controller;
	private SimpleAdapter listViewAdapter;
	private Spinner moveToWaypoint;
	private Button okButton;
	private GridView photoList;
	private long sourceWaypointId;
	private long targetWaypointId;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(
				R.menu.activity_move_photos_between_waypoints, menu);
		return true;
	}

	/**
	 * Load the data into the UI.
	 */
	private void initializeUI() {
		setContentView(R.layout.activity_move_photos_between_waypoints);
		// Attach to UI elements
		photoList = (GridView) findViewById(R.id.photoList);
		moveToWaypoint = (Spinner) findViewById(R.id.moveToWaypoint);

		cancelButton = (Button) this.findViewById(R.id.cancelButton);
		okButton = (Button) this.findViewById(R.id.okButton);

		// Attach handlers
		cancelButton.setOnClickListener(new CancelButtonHandler());
		okButton.setOnClickListener(new OKButtonHandler());
		photoList.setOnItemClickListener(new ImageClickHandler());
		moveToWaypoint.setOnItemClickListener(new SpinnerClickHandler());

	}

	/**
	 * Get data from intent and load into the form.
	 */
	private void loadData() {
		Intent intent = this.getIntent();
		sourceWaypointId = intent.getLongExtra("waypointId", 0);

		// Load waypoint list into spinner.
		List<Waypoint> waypoints = controller.getWaypoints();
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (Waypoint waypoint : waypoints) {
			// Exclude the source waypoint.
			if (waypoint.getId() != sourceWaypointId) {
				Map<String, String> datum = new HashMap<String, String>(2);
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
		moveToWaypoint.setAdapter(listViewAdapter);

		List<Image> list = controller.getImageList(sourceWaypointId);
		photoList.setAdapter(new ImageAdapter(this, list));
		// this.setTitle(controller.getWaypointDescription(sourceWaypointId));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeUI();
		loadData();
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

	private class ImageClickHandler implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			// Get the image from the adapter...
			Image image = (Image) parent.getAdapter().getItem(position);
			// ...and get its URI.
			Uri uri = Uri.parse(image.getImageURI());
			Intent viewImageIntent = new Intent(
					android.content.Intent.ACTION_VIEW);
			viewImageIntent.setDataAndType(uri, "image/jpeg");
			startActivity(viewImageIntent);
			// TODO: Here, add image to selected list.
		}
	}

	/**
	 * Handler for the OK button.
	 */
	private class OKButtonHandler implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			List<Image> images = new ArrayList<Image>();
			controller.movePhotos(targetWaypointId, images);
			finish();
			// overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
		}
	}

	private class SpinnerClickHandler implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			okButton.setEnabled(true);
		}

	}
}
