package edu.metcs683.walkabout;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.Toast;
import edu.metcs683.walkabout.controller.PhotoListController;
import edu.metcs683.walkabout.controller.WaypointDetailController;
import edu.metcs683.walkabout.model.Image;
import edu.metcs683.walkabout.uihelper.ImageAdapter;

/**
 * User interface for the photo list screen.
 * 
 * @author ryszardkilarski
 * 
 */
public class PhotoList extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

	private PhotoListController controller;

	private Uri imageURI;

	private GridView photoList;

	/**
	 * Provide an animation when pressing the back button.
	 */
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_photo_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = null;
		long id;
		Bundle bundle;

		switch (item.getItemId()) {
			case R.id.camera:
				final String cameraMessage = getString(R.string.camera_not_available_text);
				try {
					if (Camera.getNumberOfCameras() > 0) {
						intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

						// create a file to save the image
						imageURI = getOutputImageFileUri();
						// set the image file name
						intent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI);

						// start the image capture Intent
						startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

					} else {
						Toast.makeText(getApplicationContext(), cameraMessage, Toast.LENGTH_LONG).show();
					}
				} catch (final Exception ex) {
					Toast.makeText(getApplicationContext(), cameraMessage, Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.import_image:
				// TODO: Make this work.
				intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 0);
				break;
			case R.id.edit_waypoint:
				intent = new Intent(this, WaypointDetail.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				bundle = new Bundle();
				id = getIntent().getLongExtra("waypointId", 0);
				bundle.putLong("waypointId", id);
				intent.putExtras(bundle);

				startActivityForResult(intent, WaypointDetail.EDIT_WAYPOINT);

				overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
				break;
			case R.id.delete_waypoint:
				final String title = getString(R.string.delete_waypoint_text);
				final String message = getString(R.string.delete_waypoint_message);
				final String yes = getString(R.string.yes);
				final String no = getString(R.string.no);
				new AlertDialog.Builder(this).setTitle(title).setMessage(message)
						.setPositiveButton(yes, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int whichButton) {
								deleteWaypoint();
								finish();
							}
						}).setNegativeButton(no, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int whichButton) {
								// Do nothing.
							}
						}).show();
				break;
			case R.id.map_waypoint:
				intent = new Intent(this, WaypointMap.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				bundle = new Bundle();
				id = getIntent().getLongExtra("waypointId", 0);
				bundle.putLong("waypointId", id);
				intent.putExtras(bundle);

				startActivity(intent);
				break;
			case R.id.change_sort:
				controller.changeSortOrder();
				loadData();
				return true;
			case R.id.move_photos:
				intent = new Intent(this, MovePhotosBetweenWaypoints.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				bundle = new Bundle();
				id = getIntent().getLongExtra("waypointId", 0);
				bundle.putLong("waypointId", id);
				intent.putExtras(bundle);

				startActivityForResult(intent, WaypointDetail.EDIT_WAYPOINT);

				overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
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
		final long id = getIntent().getLongExtra("waypointId", 0);
		controller.deleteWaypoint(id);
	}

	/**
	 * Initialize the UI and attach to any views.
	 */
	private void initializeUI() {
		photoList = (GridView) findViewById(R.id.photoList);
		photoList.setOnItemClickListener(new ImageClickHandler());
		photoList.setOnItemLongClickListener(new ImageLongClickHandler(this));
	}

	/**
	 * Load the data into the UI.
	 */
	private void loadData() {
		final Intent intent = getIntent();
		final long id = intent.getLongExtra("waypointId", 0);
		final List<Image> list = controller.getImageList(id);
		photoList.setAdapter(new ImageAdapter(this, list));
		setTitle(controller.getWaypointDescription(id));
	}

	/**
	 * Handle the responses from all the activities.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		final String fnyi = getString(R.string.fnyi);

		switch (requestCode) {
			case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
				if (resultCode == RESULT_OK) {
					// Save image to database.
					final long id = getIntent().getLongExtra("waypointId", 0);
					final Image image = new Image(0, id, imageURI.toString());
					controller.saveImage(image);

				} else if (resultCode == RESULT_CANCELED) {
					// User cancelled the image capture
				} else {
					// Image capture failed, advise user
				}
				break;
		}
		if ((resultCode == RESULT_OK) && (data != null) && (data.getAction() == Intent.ACTION_PICK)) {
			Toast.makeText(getApplicationContext(), fnyi, Toast.LENGTH_SHORT).show();
			Toast.makeText(this, "Image saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
		}
		loadData();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_list);
		initializeUI();
		initializeControllers();
		loadData();
	}

	/**
	 * Set up any needed controllers
	 */
	private void initializeControllers() {
		controller = new PhotoListController(getApplicationContext(), this);
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

	/**
	 * Handle clicking on an image. Bring up the image viewer.
	 */
	private class ImageClickHandler implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			// Get the image from the adapter...
			final Image image = (Image) parent.getAdapter().getItem(position);
			// ...and get its URI.
			final Uri uri = Uri.parse(image.getImageURI());
			final Intent viewImageIntent = new Intent(android.content.Intent.ACTION_VIEW);
			viewImageIntent.setDataAndType(uri, "image/jpeg");
			startActivity(viewImageIntent);
		}
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
			final String title = getString(R.string.delete_image_text);
			final String message = getString(R.string.delete_image_message);
			final String yes = getString(R.string.yes);
			final String no = getString(R.string.no);

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
}
