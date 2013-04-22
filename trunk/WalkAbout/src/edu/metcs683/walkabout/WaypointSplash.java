package edu.metcs683.walkabout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Display the splash screen and then call the main activity.
 * 
 * @author Ryszard Kilarski (U81-39-8560) 
 * 
 * This class was modified from
 *         http://answers.oreilly.com/topic/2715-android-tutorial
 *         -how-to-make-a-basic-splash-screen/
 * 
 */
public class WaypointSplash extends Activity {

	protected int splashTime = 3000;
	private Thread splashTread;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.waypoint_splash);

		final WaypointSplash splashScreen = this;

		// thread for displaying the SplashScreen
		splashTread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(splashTime);
					}

				} catch (InterruptedException e) {
					//
				} finally {
					finish();
					// start a new activity
					Intent intent = new Intent(
							splashScreen.getApplicationContext(),
							WaypointList.class);
					startActivity(intent);
				}
			}
		};

		splashTread.start();
	}

	/**
	 * Function that will handle the touch
	 */
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			synchronized (splashTread) {
				splashTread.notifyAll();
			}
		}
		return true;
	}
}
