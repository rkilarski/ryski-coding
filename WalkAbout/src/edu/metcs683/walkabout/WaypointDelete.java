package edu.metcs683.walkabout;

import edu.metcs683.walkabout.controller.PhotoListController;
import android.app.Activity;
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
		super.onCreate(savedInstanceState);
		final Intent intent = getIntent();
		final long id = intent.getLongExtra("waypointId", 0);

		PhotoListController controller = new PhotoListController(this.getApplicationContext(), this);
		controller.deleteWaypoint(id);

		final Intent returnIntent = new Intent();
		returnIntent.putExtra("waypointId", id);
		setResult(Activity.RESULT_OK, returnIntent);

		finish();
		return;
	}

}
