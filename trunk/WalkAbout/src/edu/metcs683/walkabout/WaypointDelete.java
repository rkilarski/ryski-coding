package edu.metcs683.walkabout;

import edu.metcs683.walkabout.controller.WaypointDeleteController;
import edu.metcs683.walkabout.uihelper.ErrorDisplay;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Activity to delete a waypoint.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public class WaypointDelete extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			final Intent intent = getIntent();
			final long id = intent.getLongExtra("waypointId", 0);

			WaypointDeleteController controller = new WaypointDeleteController(
					this.getApplicationContext(), this);
			controller.deleteWaypoint(id);

			final Intent returnIntent = new Intent();
			returnIntent.putExtra("waypointId", id);
			setResult(Activity.RESULT_OK, returnIntent);
		} catch (Exception ex) {
			Context context = getApplicationContext();
			ErrorDisplay.displayMessage(this, context,
					context.getString(R.string.error_message_delete_waypoint), ex);
		}

		finish();
		return;
	}

}
