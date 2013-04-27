package edu.metcs683.walkabout;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import edu.metcs683.walkabout.controller.WaypointListController;
import edu.metcs683.walkabout.model.Image;
import edu.metcs683.walkabout.model.Waypoint;
import edu.metcs683.walkabout.uihelper.ErrorDisplay;
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
	private LinearLayout waypointList;
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
				try {
					final Intent intent = new Intent(this, WaypointDetail.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					startActivityForResult(intent, WaypointView.ADD_WAYPOINT);
					overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
				} catch (Exception ex) {
					Context context = getApplicationContext();
					ErrorDisplay.displayMessage(this, context, context.getString(R.string.error_message_new_waypoint),
							ex);
				}
				return true;
			case R.id.change_waypoint_sort:
				try {
					controller.changeWaypointOrder();
					loadData();
				} catch (Exception ex) {
					Context context = getApplicationContext();
					ErrorDisplay.displayMessage(this, context, context.getString(R.string.error_message_change_sort),
							ex);
				}

				return true;
			case R.id.change_photo_sort:
				try {
					controller.changePhotoOrder();
					loadData();
				} catch (Exception ex) {
					Context context = getApplicationContext();
					ErrorDisplay.displayMessage(this, context, context.getString(R.string.error_message_change_sort),
							ex);
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void initializeUI() {
		setContentView(R.layout.activity_waypoint_list);
		waypointList = (LinearLayout) findViewById(R.id.waypointListLayout);
	}

	private void loadData() {
		try {
			final List<Waypoint> waypoints = controller.getWaypoints();
			waypointList.removeAllViews();
			// Get list of all waypoints.
			for (final Waypoint waypoint : waypoints) {
				WaypointView waypointView = new WaypointView(this, this.getApplicationContext(), waypoint.getId(), this);
				waypointList.addView(waypointView);
			}
		} catch (Exception ex) {
			Context context = getApplicationContext();
			ErrorDisplay.displayMessage(this, context, context.getString(R.string.error_message_load_data), ex);
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
					try {
						id = data.getLongExtra("waypointId", 0);
						position = getViewPosition(id);
						WaypointView waypointView = new WaypointView(this, this.getApplicationContext(), id, this);
						if (controller.getWaypointOrder()) {
							waypointList.addView(waypointView); // Add to the
																// end.
						} else {
							waypointList.addView(waypointView, 0); // Add to the
																	// beginning.
						}
					} catch (Exception ex) {
						Context context = getApplicationContext();
						ErrorDisplay.displayMessage(this, context,
								context.getString(R.string.error_message_new_waypoint), ex);
					}
					break;
				case WaypointView.EDIT_WAYPOINT:
					try {
						id = data.getLongExtra("waypointId", 0);
						position = getViewPosition(id);
						view = (WaypointView) waypointList.getChildAt(position);
						view.updateWaypointAttributes();
					} catch (Exception ex) {
						Context context = getApplicationContext();
						ErrorDisplay.displayMessage(this, context,
								context.getString(R.string.error_message_edit_waypoint), ex);
					}
					break;
				case WaypointView.DELETE_WAYPOINT:
					try {
						id = data.getLongExtra("waypointId", 0);
						position = getViewPosition(id);
						waypointList.removeViewAt(position);
					} catch (Exception ex) {
						Context context = getApplicationContext();
						ErrorDisplay.displayMessage(this, context,
								context.getString(R.string.error_message_edit_waypoint), ex);
					}
					break;
				case WaypointView.MOVE_PHOTOS:
					try {
						id = data.getLongExtra("waypointId", 0);
						final long id2 = data.getLongExtra("waypointId2", 0);
						view = (WaypointView) waypointList.getChildAt(getViewPosition(id));
						view.updateWaypointPhotos();
						view = (WaypointView) waypointList.getChildAt(getViewPosition(id2));
						view.updateWaypointPhotos();
					} catch (Exception ex) {
						Context context = getApplicationContext();
						ErrorDisplay.displayMessage(this, context,
								context.getString(R.string.error_message_move_photos), ex);
					}
					break;
				case WaypointView.DELETE_PHOTOS_FROM_WAYPOINT:
					try {
						id = data.getLongExtra("waypointId", 0);
						view = (WaypointView) waypointList.getChildAt(getViewPosition(id));
						view.updateWaypointPhotos();
					} catch (Exception ex) {
						Context context = getApplicationContext();
						ErrorDisplay.displayMessage(this, context,
								context.getString(R.string.error_message_delete_photos), ex);
					}
					break;
				case WaypointView.ADD_NEW_PHOTO:
					try { // Save image to database.
						String filePath = imageUri.toString();
						long waypointId = getWaypointIdFromFilename(filePath);
						final Image image = new Image(0, waypointId, filePath);
						controller.saveImage(image);

						view = (WaypointView) waypointList.getChildAt(getViewPosition(waypointId));
						view.updateWaypointPhotos();
					} catch (Exception ex) {
						Context context = getApplicationContext();
						ErrorDisplay.displayMessage(this, context,
								context.getString(R.string.error_message_new_waypoint), ex);
					}
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
		for (int i = 0; i < waypointList.getChildCount(); i++) {
			View v = waypointList.getChildAt(i);
			if ((v instanceof WaypointView) && ((((WaypointView) v).getWaypointId()) == id)) {
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
