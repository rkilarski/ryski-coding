package edu.bu.metcs683.restaurantfinder;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ReviewCriteria extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review_criteria);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_review_criteria, menu);
		return true;
	}

}
