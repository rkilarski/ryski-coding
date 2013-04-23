package edu.metcs683.walkabout.uihelper;

import android.net.Uri;

/**
 * This interface is the observer for a camera photo. The issue is that the
 * camera app will not return either the URI or any extra data that is bundled
 * with the intent call to the onActivityResult() method.
 * 
 * So I had to encode the waypoint id (the needed extra data) into the file name
 * of the photo. Then, I implemented the Observer pattern between the
 * WaypointView and the WaypointList.
 * 
 * The WaypointList is the Observer. When the WaypointView calls the camera app,
 * it notifies the observer of the generated file Uri, which includes the
 * encoded waypoint id. The WaypointList can then save the Uri under the
 * appropriate waypoint and refresh the screen appropriately.
 * 
 * @author Ryszard Kilarski (U81-39-8560)
 * 
 */
public interface ImageObserver {

	public void setImageUri(Uri uri);

}
