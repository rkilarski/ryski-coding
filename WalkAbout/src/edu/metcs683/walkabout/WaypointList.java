package edu.metcs683.walkabout;

import edu.metcs683.walkabout.controller.WaypointListController;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

/**
 * Main user interface for WalkAbout. This is the Waypoint List UI.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class WaypointList extends Activity {

	private WaypointListController controller;
	private ListView waypointList;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_waypoint_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_waypoint:
			Intent intent = new Intent(this, WaypointDetail.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			startActivityForResult(intent, WaypointDetail.ADD_WAYPOINT);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void initializeUI() {
		setContentView(R.layout.activity_waypoint_list);
		// Attach to UI elements
		waypointList = (ListView) findViewById(R.id.waypointList);

		// Attach handlers
	}

	private void loadData() {
		// TODO
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case WaypointDetail.EDIT_WAYPOINT:
			// TODO
			break;
		case WaypointDetail.ADD_WAYPOINT:
			// TODO
			break;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeUI();
		controller = new WaypointListController(getApplicationContext());
		loadData();
	}

	/**
	 * Handler to edit an existing waypoint.
	 */
	private class EditWaypointListener implements OnClickListener {
		private Context context;
		private long waypointId;

		public EditWaypointListener(long waypointId, Context context) {
			this.waypointId = waypointId;
			this.context = context;
		}

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(context, WaypointDetail.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			Bundle bundle = new Bundle();
			bundle.putLong("waypointId", waypointId);
			intent.putExtras(bundle);
			startActivityForResult(intent, WaypointDetail.EDIT_WAYPOINT);
		}

	}

}
