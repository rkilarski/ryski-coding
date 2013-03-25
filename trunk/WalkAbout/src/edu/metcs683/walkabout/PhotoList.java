package edu.metcs683.walkabout;

import java.util.List;

import edu.metcs683.walkabout.controller.PhotoListController;
import edu.metcs683.walkabout.model.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * User interface for the photo list screen.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class PhotoList extends Activity {

	private PhotoListController controller;
	private GridView photoList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_list);
		initializeUI();
		controller = new PhotoListController(getApplicationContext());
		loadData();
	}

	private void initializeUI() {
		photoList = (GridView) findViewById(R.id.photoList);

		photoList.setOnItemClickListener(new ImageClickHandler());
	}

	private void loadData() {
		Intent intent = this.getIntent();
		long id = intent.getLongExtra("waypointId", 0);
		List<Image> list = controller.getImageList(id);
		photoList.setAdapter(new ImageAdapter(this, list));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo_list, menu);
		return true;
	}

	private class ImageClickHandler implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			Toast.makeText(getApplicationContext(), "" + position,
					Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * Adapter class for the images.
	 */
	private class ImageAdapter extends BaseAdapter {
		private Context context;
		List<Image> list;

		public ImageAdapter(Context context, List<Image> imageList) {
			this.context = context;
			this.list = imageList;
		}

		public int getCount() {
			return list.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// create a new ImageView for each item referenced by the Adapter
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) { // if it's not recycled, initialize some
										// attributes
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) convertView;
			}

			imageView.setImageURI(Uri.parse(list.get(position).getImageURI()));
			return imageView;
		}

	}
}
