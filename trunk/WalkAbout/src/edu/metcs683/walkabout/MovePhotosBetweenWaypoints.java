package edu.metcs683.walkabout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MovePhotosBetweenWaypoints extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_photos_between_waypoints);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_move_photos_between_waypoints, menu);
        return true;
    }
    
}
