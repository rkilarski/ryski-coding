package edu.metcs683.walkabout.uihelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import edu.metcs683.walkabout.R;
import edu.metcs683.walkabout.WaypointDelete;
import edu.metcs683.walkabout.WaypointDetail;
import edu.metcs683.walkabout.WaypointMap;
import edu.metcs683.walkabout.WaypointPhotoMove;
import edu.metcs683.walkabout.controller.WaypointViewController;
import edu.metcs683.walkabout.model.Image;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;

/**
 * Custom compound view that contains all the images for one waypoint, along
 * with all the waypoint-specific functionality that goes along with it.
 * 
  * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class WaypointView extends LinearLayout {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

	private Activity activity;
	private ImageButton cameraButton;
	private Context context;
	private WaypointViewController controller;
	private Uri imageURI;
	private ImageButton menuButton;
	private GridView photoList;
	private long waypointId;
	private TextView waypointTitle;

	/**
	 * Public constructor to create this custom control. Pass in the activity,
	 * context, and waypoint id.
	 * 
	 * @param activity
	 * @param context
	 * @param id
	 */
	public WaypointView(Activity activity, Context context, long id) {
		this(context, null);
		this.context = context;
		this.activity = activity;
		this.waypointId = id;
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.waypoint, this);
		initializeUI();
		initializeControllers();
		loadData();
	}

	public WaypointView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * Show popup settings menu.
	 * 
	 * @param v
	 */
	public void showWaypointMenu(View v) {
		PopupMenu popup = new PopupMenu(context, v);
		popup.inflate(R.menu.waypoint_view);
		popup.setOnMenuItemClickListener(new MenuItemClickHandler());
		popup.show();
	}

	/**
	 * Ability to delete this image.
	 */
	private void deleteImage(Image image) {
		controller.deleteImage(image.getId());
	}

	/**
	 * Ability to delete this waypoint.
	 */
	private void deleteWaypoint() {
		controller.deleteWaypoint(waypointId);
	}

	/**
	 * Set up any needed controllers
	 */
	private void initializeControllers() {
		controller = new WaypointViewController(context, activity);
	}

	private void initializeUI() {
		// Attach to UI elements
		waypointTitle = (TextView) findViewById(R.id.waypointTitle);
		cameraButton = (ImageButton) findViewById(R.id.cameraButton);
		menuButton = (ImageButton) findViewById(R.id.menuButton);
		photoList = (GridView) findViewById(R.id.photoList);

		// Attach handlers
		cameraButton.setOnClickListener(new OnCameraClickHandler());
		menuButton.setOnClickListener(new MenuDisplayClickHandler());
		photoList.setOnItemLongClickListener(new ImageLongClickHandler(context));
	}

	/**
	 * Load the data into the UI.
	 */
	private void loadData() {
		updateWaypointAttributes();
		updateWaypointPhotos();
	}

	/**
	 * If the waypoint changes, update its attributes.
	 */
	public void updateWaypointAttributes() {
		waypointTitle.setText(controller.getWaypointDescription(waypointId));
	}

	/**
	 * If the waypoint photos change, update them.
	 */
	public void updateWaypointPhotos() {
		final List<Image> list = controller.getImageList(waypointId);
		photoList.setAdapter(new ImageAdapter(context, list));
	}

	/**
	 * Create a File for saving an image.
	 */
	private static File getOutputImageFile() {
		final File mediaStorageDir = new File(
				Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "WalkAbout");

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("WalkAbout", "failed to create directory " + mediaStorageDir.getParentFile());
				return null;
			}
		}

		// Create a media file name
		final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File mediaFile;
		mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");

		return mediaFile;
	}

	/**
	 * Create file URI for image.
	 * 
	 * @return
	 */
	private static Uri getOutputImageFileUri() {
		return Uri.fromFile(getOutputImageFile());
	}

	public long getWaypointId() {
		return this.waypointId;
	}

	/**
	 * Handle a long click on an image. Delete the image.
	 */
	private class ImageLongClickHandler implements OnItemLongClickListener {

		Context context;

		private ImageLongClickHandler(Context context) {
			this.context = context;
		}

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View arg1, int position, long arg3) {
			// Get the image from the adapter...
			final Image image = (Image) parent.getAdapter().getItem(position);
			final String title = context.getString(R.string.delete_image_text);
			final String message = context.getString(R.string.delete_image_message);
			final String yes = context.getString(R.string.yes);
			final String no = context.getString(R.string.no);

			new AlertDialog.Builder(context).setTitle(title).setMessage(message)
					.setPositiveButton(yes, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int whichButton) {
							deleteImage(image);
							loadData();
						}
					}).setNegativeButton(no, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int whichButton) {
							// Do nothing.
						}
					}).show();

			return false;
		}
	}

	/**
	 * Handler for the camera button.
	 */
	private class OnCameraClickHandler implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			Intent intent = null;

			final String cameraMessage = context.getString(R.string.camera_not_available_text);
			try {
				if (Camera.getNumberOfCameras() > 0) {
					intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

					// create a file to save the image
					imageURI = getOutputImageFileUri();
					// set the image file name
					intent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI);

					// start the image capture Intent
					activity.startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

				} else {
					Toast.makeText(context.getApplicationContext(), cameraMessage, Toast.LENGTH_LONG).show();
				}
			} catch (final Exception ex) {
				Toast.makeText(context.getApplicationContext(), cameraMessage, Toast.LENGTH_LONG).show();
			}
		}

	}

	/**
	 * Handler to display popup menu item.
	 */
	private class MenuDisplayClickHandler implements OnClickListener {

		@Override
		public void onClick(View v) {
			showWaypointMenu(v);
		}

	}

	/**
	 * Handler to perform popup menu items.
	 */
	private class MenuItemClickHandler implements PopupMenu.OnMenuItemClickListener {
		@Override
		public boolean onMenuItemClick(MenuItem item) {
			Intent intent = null;
			Bundle bundle;

			switch (item.getItemId()) {
				case R.id.edit_waypoint:
					intent = new Intent(context, WaypointDetail.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

					bundle = new Bundle();
					bundle.putLong("waypointId", waypointId);
					intent.putExtras(bundle);

					activity.startActivityForResult(intent, WaypointDetail.EDIT_WAYPOINT);
					activity.overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
					break;
				case R.id.delete_waypoint:
					final String title = context.getString(R.string.delete_waypoint_text);
					final String message = context.getString(R.string.delete_waypoint_message);
					final String yes = context.getString(R.string.yes);
					final String no = context.getString(R.string.no);
					new AlertDialog.Builder(activity).setTitle(title).setMessage(message)
							.setPositiveButton(yes, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int whichButton) {
									Intent intent = new Intent(activity, WaypointDelete.class);
									Bundle bundle = new Bundle();
									// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

									bundle.putLong("waypointId", waypointId);
									intent.putExtras(bundle);

									activity.startActivityForResult(intent, WaypointDetail.DELETE_WAYPOINT);
								}
							}).setNegativeButton(no, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int whichButton) {
									// Do nothing.
								}
							}).show();
					break;
				case R.id.map_waypoint:
					intent = new Intent(context, WaypointMap.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

					bundle = new Bundle();
					bundle.putLong("waypointId", waypointId);
					intent.putExtras(bundle);

					context.startActivity(intent);
					break;
				case R.id.change_sort:
					controller.changeSortOrder();
					loadData();
					return true;
				case R.id.move_photos:
					intent = new Intent(context, WaypointPhotoMove.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

					bundle = new Bundle();
					bundle.putLong("waypointId", waypointId);
					intent.putExtras(bundle);

					activity.startActivityForResult(intent, WaypointDetail.MOVE_PHOTOS);
					activity.overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
					break;
			}
			return true;
		}
	}
}
