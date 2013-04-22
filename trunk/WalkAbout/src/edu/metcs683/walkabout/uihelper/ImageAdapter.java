package edu.metcs683.walkabout.uihelper;

import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import edu.metcs683.walkabout.model.Image;

/**
 * Adapter class for the images.
 * @author Ryszard Kilarski (U81-39-8560)
 */
public class ImageAdapter extends BaseAdapter {
	private final Context context;
	List<Image> list;

	public ImageAdapter(Context context, List<Image> imageList) {
		this.context = context;
		this.list = imageList;
	}

	@Override
	public int getCount() {
		return this.list.size();
	}

	@Override
	public Image getItem(int position) {
		return this.list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return this.list.get(position).getId();
	}

	// create a new ImageView for each item referenced by the Adapter
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			imageView = new ImageView(this.context);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(5, 5, 5, 5);
		} else {
			imageView = (ImageView) convertView;
		}

		final Uri uri = Uri.parse(this.list.get(position).getImageURI());
		imageView.setImageURI(uri);
		return imageView;
	}

}
