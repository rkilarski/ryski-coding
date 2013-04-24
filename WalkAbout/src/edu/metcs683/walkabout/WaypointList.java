package edu.metcs683.walkabout;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import edu.metcs683.walkabout.controller.WaypointListController;
import edu.metcs683.walkabout.model.Image;
import edu.metcs683.walkabout.model.Waypoint;
import edu.metcs683.walkabout.uihelper.ImageObserver;
import edu.metcs683.walkabout.uihelper.WaypointView;

/**
 * Main user interface for WalkAbout. This is the Waypoint List UI.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class WaypointList extends Activity implements ImageObserver {

	protected Dialog splashDialog;
	private WaypointListController controller;
	private LinearLayout layout;
	private Uri imageUri;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_waypoint_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_waypoint:
			final Intent intent = new Intent(this, WaypointDetail.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			startActivityForResult(intent, WaypointView.ADD_WAYPOINT);
			overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
			return true;
		case R.id.change_waypoint_sort:
			controller.changeWaypointOrder();
			loadData();
			return true;
		case R.id.change_photo_sort:
			controller.changePhotoOrder();
			loadData();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void initializeUI() {
		setContentView(R.layout.activity_waypoint_list);
		layout = (LinearLayout) findViewById(R.id.LinearLayout1);
	}

	private void loadData() {
		final List<Waypoint> waypoints = controller.getWaypoints();
		layout.removeAllViews();
		// Get list of all waypoints.
		for (final Waypoint waypoint : waypoints) {
			WaypointView waypointView = new WaypointView(this,
					this.getApplicationContext(), waypoint.getId(), this);
			layout.addView(waypointView);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			final long id;
			final int position;
			WaypointView view;

			switch (requestCode) {
			case WaypointView.ADD_WAYPOINT:
				id = data.getLongExtra("waypointId", 0);
				position = getViewPosition(id);
				WaypointView waypointView = new WaypointView(this,
						this.getApplicationContext(), id, this);
				if (controller.getWaypointOrder()) {
					layout.addView(waypointView); // Add to the end.
				} else {
					layout.addView(waypointView, 0); // Add to the beginning.
				}
				break;
			case WaypointView.EDIT_WAYPOINT:
				id = data.getLongExtra("waypointId", 0);
				position = getViewPosition(id);
				view = (WaypointView) layout.getChildAt(position);
				view.updateWaypointAttributes();
				break;
			case WaypointView.DELETE_WAYPOINT:
				id = data.getLongExtra("waypointId", 0);
				position = getViewPosition(id);
				layout.removeViewAt(position);
				break;
			case WaypointView.REORDER_WAYPOINT:
				/*
				 * id = data.getLongExtra("waypointId", 0); position =
				 * getViewPosition(id); view = (WaypointView)
				 * layout.getChildAt(position); view.updateWaypointPhotos();
				 */
				break;
			case WaypointView.MOVE_PHOTOS:
				id = data.getLongExtra("waypointId", 0);
				final long id2 = data.getLongExtra("waypointId2", 0);
				view = (WaypointView) layout.getChildAt(getViewPosition(id));
				view.updateWaypointPhotos();
				view = (WaypointView) layout.getChildAt(getViewPosition(id2));
				view.updateWaypointPhotos();
				break;
			case WaypointView.ADD_NEW_PHOTO:
				// Save image to database.
				String filePath = imageUri.toString();
				long waypointId = getWaypointIdFromFilename(filePath);
				final Image image = new Image(0, waypointId, filePath);
				controller.saveImage(image);

				view = (WaypointView) layout
						.getChildAt(getViewPosition(waypointId));
				view.updateWaypointPhotos();
				break;
			}
		}
	}

	/**
	 * Implementation of the Observer pattern for taking photos.
	 */
	@Override
	public void setImageUri(Uri uri) {
		this.imageUri = uri;
	}

	/**
	 * The image URI has encoded within it the waypoint id. This method gets it.
	 * 
	 * @param imageURI
	 * @return
	 */
	private long getWaypointIdFromFilename(String filePath) {
		String[] array = filePath.split("_");
		String string = array[2];
		String[] array2 = string.split("\\.");
		long id = Long.parseLong(array2[0]);
		return id;
	}

	/**
	 * Given a waypoint id, find its position in the list of layouts.
	 * 
	 * @param id
	 * @return
	 */
	private int getViewPosition(long id) {
		for (int i = 0; i < layout.getChildCount(); i++) {
			View v = layout.getChildAt(i);
			if ((v instanceof WaypointView)
					&& ((((WaypointView) v).getWaypointId()) == id)) {
				return i;
			}
		}
		return 0;
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
		controller = new WaypointListController(getApplicationContext(), this);
	}

	/**
	 * Removes the Dialog that displays the splash screen
	 */
	protected void removeSplashScreen() {
		if (splashDialog != null) {
			splashDialog.dismiss();
			splashDialog = null;
		}
	}
}
