package edu.metcs683.walkabout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.metcs683.walkabout.controller.WaypointListController;
import edu.metcs683.walkabout.model.Waypoint;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
		waypointList.setOnItemClickListener(new ListItemClickListener());
	}

	private void loadData() {
		List<Waypoint> waypoints = controller.getWaypoints();
		// Get list of all waypoints.
		/*
		 * ArrayAdapter<Waypoint> adapter = new ArrayAdapter<Waypoint>(this,
		 * android.R.layout.simple_list_item_1, android.R.id.text1, waypoints);
		 */
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (Waypoint waypoint : waypoints) {
			Map<String, String> datum = new HashMap<String, String>(2);
			datum.put("description", waypoint.getDescription());
			datum.put("date", waypoint.getDateTime().toString());
			datum.put("id", Long.toString(waypoint.getId()));
			data.add(datum);
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				android.R.layout.simple_list_item_2, new String[] {
						"description", "date" }, new int[] {
						android.R.id.text1, android.R.id.text2 });
		waypointList.setAdapter(adapter);
		// TODO
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case WaypointDetail.EDIT_WAYPOINT:
			loadData();
			break;
		case WaypointDetail.ADD_WAYPOINT:
			loadData();
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

	/**
	 * Handler to view photos for a particular Waypoint.
	 */
	private class ListItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View v, int position,
				long id) {
			Intent intent = new Intent(getApplicationContext(), PhotoList.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			Bundle bundle = new Bundle();
			//TODO:  Get right ID.
			bundle.putLong("waypointId", id);
			intent.putExtras(bundle);
			startActivityForResult(intent, WaypointDetail.EDIT_WAYPOINT);
		}

	}
}
