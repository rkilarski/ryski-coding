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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import edu.metcs683.walkabout.controller.WaypointListController;
import edu.metcs683.walkabout.model.Waypoint;
import edu.metcs683.walkabout.uihelper.WaypointView;

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
	private LinearLayout layout;

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
		layout = (LinearLayout) findViewById(R.id.LinearLayout1);
		// Attach to UI elements
		// rkilarski waypointList = (ListView) findViewById(R.id.waypointList);

		// Attach handlers
		// rkilarski waypointList.setOnItemClickListener(new
		// ListItemClickHandler());
	}

	private void loadData() {
		final List<Waypoint> waypoints = controller.getWaypoints();
		// Get list of all waypoints.
		final List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (final Waypoint waypoint : waypoints) {
			WaypointView waypointView = new WaypointView(this, this.getApplicationContext(), waypoint.getId());
			layout.addView(waypointView);
			/* rkilarski
			 * final Map<String, String> datum = new HashMap<String, String>(2);
			 * datum.put("description", waypoint.getDescription());
			 * datum.put("date", waypoint.getDateTime().toString());
			 * datum.put("id", Long.toString(waypoint.getId()));
			 * data.add(datum);
			 */
		}
		/*
		 * listViewAdapter = new SimpleAdapter(this, data,
		 * android.R.layout.simple_list_item_2, new String[] { "description",
		 * "date" }, new int[] { android.R.id.text1, android.R.id.text2 });
		 * waypointList.setAdapter(listViewAdapter);
		 */
		// TODO
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		final long id = data.getLongExtra("waypointId", 0);
		final int position = getViewPosition(id);
		WaypointView view;
		
		switch (requestCode) {
			case WaypointDetail.ADD_WAYPOINT:
				WaypointView waypointView = new WaypointView(this, this.getApplicationContext(), id);
				if (controller.getWaypointOrder()) {
					layout.addView(waypointView); // Add to the end.
				} else {
					layout.addView(waypointView, 0); // Add to the beginning.
				}
				break;
			case WaypointDetail.EDIT_WAYPOINT:
				view = (WaypointView) layout.getChildAt(position);
				view.updateWaypointAttributes();
				break;
			case WaypointDetail.DELETE_WAYPOINT:
				layout.removeViewAt(position);
				break;
			case WaypointDetail.REORDER_WAYPOINT:
				view = (WaypointView) layout.getChildAt(position);
				view.updateWaypointPhotos();
				break;
			case WaypointDetail.MOVE_PHOTOS:
				loadData();
				break;
		}
	}

	private int getViewPosition(long id) {
		for (int i = 0; i < layout.getChildCount(); i++) {
			View v = layout.getChildAt(i);
			if ((v instanceof WaypointView) && ((((WaypointView) v).getWaypointId()) == id)) {
				return i;
			}
		}
		return 0;
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
	private void initializeControllers() {
		controller = new WaypointListController(getApplicationContext(), this);
	}

	/**
	 * Handler to view photos for a particular Waypoint.
	 */
	private class ListItemClickHandler implements OnItemClickListener {

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
