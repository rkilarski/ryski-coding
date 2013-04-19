package edu.metcs683.walkabout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import edu.metcs683.walkabout.controller.WaypointListController;
import edu.metcs683.walkabout.model.Waypoint;

/**
 * Main user interface for WalkAbout. This is the Waypoint List UI.
 * 
 * @author Ryszard Kilarski
 * 
 */
public class WaypointList extends Activity {

	private WaypointListController controller;
	private SimpleAdapter listViewAdapter;
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
				final Intent intent = new Intent(this, WaypointDetail.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				startActivityForResult(intent, WaypointDetail.ADD_WAYPOINT);
				overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
				return true;
			case R.id.change_sort:
				controller.changeSortOrder();
				loadData();
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
		final List<Waypoint> waypoints = controller.getWaypoints();
		// Get list of all waypoints.
		/*
		 * ArrayAdapter<Waypoint> adapter = new ArrayAdapter<Waypoint>(this,
		 * android.R.layout.simple_list_item_1, android.R.id.text1, waypoints);
		 */
		final List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (final Waypoint waypoint : waypoints) {
			final Map<String, String> datum = new HashMap<String, String>(2);
			datum.put("description", waypoint.getDescription());
			datum.put("date", waypoint.getDateTime().toString());
			datum.put("id", Long.toString(waypoint.getId()));
			data.add(datum);
		}
		listViewAdapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2, new String[] {
				"description", "date" }, new int[] { android.R.id.text1, android.R.id.text2 });
		waypointList.setAdapter(listViewAdapter);
		// TODO
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
			case WaypointDetail.ADD_WAYPOINT:
				break;
		}
		loadData();
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
	private void initializeControllers(){
		controller = new WaypointListController(getApplicationContext(), this);		
	}
	/**
	 * Handler to view photos for a particular Waypoint.
	 */
	private class ListItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
			final Intent intent = new Intent(getApplicationContext(), PhotoList.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			final Bundle bundle = new Bundle();

			// Get id from the listview adapter.
			final Map<String, String> row = (HashMap<String, String>) listViewAdapter.getItem(position);
			bundle.putLong("waypointId", Long.parseLong(row.get("id")));
			intent.putExtras(bundle);
			startActivityForResult(intent, WaypointDetail.EDIT_WAYPOINT);
			overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
		}

	}
}
