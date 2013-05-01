package edu.metcs683.walkabout.uihelper;

import edu.metcs683.walkabout.R;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.widget.Toast;

/**
 * Utility class to encapsulate gps/location services.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class LocationService {
	/**
	 * Return the location from the GPS/network provider.
	 * 
	 * @return
	 */
	public static Location getLocation(Activity activity) {
		Location location = null;
		try {
			final LocationManager locationManager = (LocationManager) activity
					.getSystemService(Context.LOCATION_SERVICE);
			final boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			if (enabled) {
				final Criteria criteria = new Criteria();
				final String provider = locationManager.getBestProvider(criteria, true);
				location = locationManager.getLastKnownLocation(provider);
				if (location == null) {
					// What do we do?
					location = new Location("network"); // Try returning network
														// location.
					Context context = activity.getApplicationContext();
					Toast.makeText(context, context.getString(R.string.error_message_location_not_available),
							Toast.LENGTH_LONG).show();
				}
			}
		} catch (Exception ex) {
			Context context = activity.getApplicationContext();
			Toast.makeText(context, context.getString(R.string.error_message_location_not_available), Toast.LENGTH_LONG)
					.show();
		}
		return location;
	}
}
