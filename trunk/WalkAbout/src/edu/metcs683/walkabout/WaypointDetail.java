package edu.metcs683.walkabout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class WaypointDetail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initializeUI();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_waypoint_detail, menu);
		return true;
	}

	private void initializeUI(){
		setContentView(R.layout.activity_waypoint_detail);
	}

}
