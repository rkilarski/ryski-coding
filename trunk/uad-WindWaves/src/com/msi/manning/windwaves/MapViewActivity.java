package com.msi.manning.windwaves;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.msi.manning.windwaves.data.BuoyData;
import com.msi.manning.windwaves.data.NDBCFetcher;

import java.util.ArrayList;

// NOTE apiKey stuff in layout file - note that that seems dumb (in the layout, not the manifest?)
// NOTE lastKnownLocation via emulator may be NULL though, need to set at least one location
// (w/tools) for it to not be
// NOTE have to setBounds on defaultMarker
// NOTE can also create a receiver for LOCATION_CHANGED (alternative way to get updated)
// NOTE can also get lastLocation and locationChanged callback from MyLocationOverlay (more coarse
// grained though)
// NOTE can also get location using CellLocation via TelephonyManager
// NOTE can use Geocode to go back and forth with lat/long addresses/places

/**
 * MapView Activity for WindWaves.
 * 
 * @author charliecollins
 * 
 */
public class MapViewActivity extends MapActivity {

    private static final String CLASSTAG = MapViewActivity.class.getSimpleName();

    private static final int MENU_SET_SATELLITE = 1;
    private static final int MENU_SET_MAP = 2;
    private static final int MENU_BUOYS_FROM_MAP_CENTER = 3;
    private static final int MENU_BACK_TO_LAST_LOCATION = 4;

    private final Handler handler = new Handler() {

        @Override
        public void handleMessage(final Message msg) {
            Log.d(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG
                + " handleMessage invoked - update overlays with new data");
            Log.d(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + "   buoys (List<BuoyOverlayItem>) size - "
                + buoys.size());

            progressDialog.dismiss();

            // clear the buoys itemized overlay - if it's already there
            if (mapView.getOverlays().contains(buoyOverlay)) {
                mapView.getOverlays().remove(buoyOverlay);
            }

            // add buoys itemized overlay
            buoyOverlay = new BuoyItemizedOverlay(buoys, defaultMarker, MapViewActivity.this);
            mapView.getOverlays().add(buoyOverlay);

            // invalidate so redrawn with icons (without this, not drawn until touch)
            mapView.invalidate();
        }
    };

    private final LocationListener locationListenerGetBuoyData = new LocationListener() {

        public void onLocationChanged(final Location loc) {
            Log.v(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG
                + "   locationProviderGetBuoyData LOCATION CHANGED - " + loc);
            int lat = (int) (loc.getLatitude() * LocationHelper.MILLION);
            int lon = (int) (loc.getLongitude() * LocationHelper.MILLION);
            // update buoy data
            GeoPoint geoPoint = new GeoPoint(lat, lon);
            getBuoyData(geoPoint);
        }

        public void onProviderDisabled(final String s) {
        }

        public void onProviderEnabled(final String s) {
        }

        public void onStatusChanged(final String s, final int i, final Bundle b) {
        }
    };

    private final LocationListener locationListenerRecenterMap = new LocationListener() {

        public void onLocationChanged(Location loc) {
            Log.v(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + "   locationProvider LOCATION CHANGED - " + loc);
            int lat = (int) (loc.getLatitude() * LocationHelper.MILLION);
            int lon = (int) (loc.getLongitude() * LocationHelper.MILLION);
            // animate to new location
            GeoPoint geoPoint = new GeoPoint(lat, lon);
            mapController.animateTo(geoPoint);
        }

        public void onProviderDisabled(final String s) {
        }

        public void onProviderEnabled(final String s) {
        }

        public void onStatusChanged(final String s, final int i, final Bundle b) {
        }
    };

    private MapController mapController;
    private LocationManager locationManager;
    private LocationProvider locationProvider;
    private MapView mapView;
    private Overlay buoyOverlay;
    private ProgressDialog progressDialog;
    private Drawable defaultMarker;
    private ArrayList<BuoyOverlayItem> buoys;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        Log.v(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + " onCreate");
        setContentView(R.layout.mapview_activity);

        mapView = (MapView) findViewById(R.id.map_view);
        mapView.setBuiltInZoomControls(true);

        defaultMarker = getResources().getDrawable(R.drawable.buoy);
        defaultMarker.setBounds(0, 0, defaultMarker.getIntrinsicWidth(), defaultMarker
            .getIntrinsicHeight());

        buoys = new ArrayList<BuoyOverlayItem>();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + " onStart");
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);

        Log.v(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + "   locationProvider from criteria - "
            + locationProvider);

        // get location updates from locationProvider
        // we set minTime(milliseconds) and minDistance(meters) to low values here to get updated
        // often (for emulator/debug)
        // in real life you *DO NOT* want to do this, it may consume too many resources
        // see LocationMangaer in JavaDoc for guidelines (time less than 60000ms for minTime is NOT
        // recommended)
        //
        // we use separate locationListeners for different purposes
        // one to update buoy data only if we move a long distance (185000 meters, just under the
        // 100 nautical miles we are parsing the data for)
        // another to recenter the map, even when we move a short distance (1000 meters)
        if (locationProvider != null) {
            locationManager.requestLocationUpdates(locationProvider.getName(), 3000, 185000,
                locationListenerGetBuoyData);
            locationManager.requestLocationUpdates(locationProvider.getName(), 3000, 1000,
                locationListenerRecenterMap);
        } else {
            Log.e(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + "  NO LOCATION PROVIDER AVAILABLE");
            Toast.makeText(this,
                "Wind and Waves cannot continue, the GPS location provider is not available at this time.",
                Toast.LENGTH_LONG).show();
            finish();
        }

        // animate to, and get buoy data for lastKnownPoint on startup (or fake/prime point if no
        // last known)
        GeoPoint lastKnownPoint = getLastKnownPoint();
        mapController = mapView.getController();
        mapController.setZoom(10);
        mapController.animateTo(lastKnownPoint);
        getBuoyData(lastKnownPoint);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MapViewActivity.MENU_SET_MAP, 0, "Map").setIcon(android.R.drawable.ic_menu_mapmode);
        menu.add(0, MapViewActivity.MENU_SET_SATELLITE, 0, "Satellite").setIcon(android.R.drawable.ic_menu_mapmode);
        menu.add(1, MapViewActivity.MENU_BUOYS_FROM_MAP_CENTER, 0, "Get Buoy Data").setIcon(
            android.R.drawable.ic_menu_directions);
        menu.add(2, MapViewActivity.MENU_BACK_TO_LAST_LOCATION, 0, "My Location").setIcon(
            android.R.drawable.ic_menu_mylocation);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(final int featureId, final MenuItem item) {
        switch (item.getItemId()) {
            case MapViewActivity.MENU_SET_MAP:
                mapView.setSatellite(false);
                break;
            case MapViewActivity.MENU_SET_SATELLITE:
                mapView.setSatellite(true);
                break;
            case MapViewActivity.MENU_BUOYS_FROM_MAP_CENTER:
                getBuoyData(mapView.getMapCenter());
                break;
            case MapViewActivity.MENU_BACK_TO_LAST_LOCATION:
                mapController.animateTo(getLastKnownPoint());
                getBuoyData(getLastKnownPoint());
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    // required by MapActivity for maps server to be notified if you are displaying directions
    @Override
    public boolean isRouteDisplayed() {
        return false;
    }

    private GeoPoint getLastKnownPoint() {
        GeoPoint lastKnownPoint = null;

        // last KNOWN may be null, if none set after power up, or in emulator and not setup
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Log.i(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + "  lastKnownLocation - " + lastKnownLocation);

        // get lastKnown GeoPoint (either from lastKnownLocation, or prime the pump manually)
        if (lastKnownLocation != null) {
            lastKnownPoint = LocationHelper.getGeoPoint(lastKnownLocation);            
        } else {
            Log.w(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG
                + "  lastKnownLocation NULL - override to Golden Gate (gotta start somewhere)");
            lastKnownPoint = LocationHelper.GOLDEN_GATE;

            Toast.makeText(this,
                "Wind and Waves cannot determine your location at startup, last known location is not present - defaulting to Golden Gate (enable GPS and then use menu->My Location).",
                Toast.LENGTH_LONG).show();
        }
        
        return lastKnownPoint;
    }

    private void getBuoyData(final GeoPoint point) {
        Log.d(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + "  getBuoyData invoked");
        progressDialog = ProgressDialog.show(this, "Processing . . .", "Getting buoy data", true, false);

        new Thread() {

            @Override
            public void run() {
                // parse lat/lon from GeoPoint back into Strings with direction (37N, 112W - etc)
                String lats = LocationHelper.parsePoint(point.getLatitudeE6() / LocationHelper.MILLION, true);
                String lons = LocationHelper.parsePoint(point.getLongitudeE6() / LocationHelper.MILLION, false);

                // for now we hard code the radius to 100 nautical miles from current point
                // (enhancement, dynamically set radius based on map zoom bounds, or add user prefs)
                NDBCFetcher fetcher = new NDBCFetcher(lats, lons, "100");
                long start = System.currentTimeMillis();
                Log.d(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + " fetcher start");
                ArrayList<BuoyData> buoyDataList = fetcher.getData();
                Log.d(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + " fetcher finish - duration = "
                    + (System.currentTimeMillis() - start));
                Log.d(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + " buoyDataList size = " + buoyDataList.size());

                // parse the List<BuoyData> from network call into a List<BuoyOverlayItem>
                buoys = getBuoyOverlayItems(buoyDataList);

                // send message to Handler to update UI
                handler.sendEmptyMessage(1);
            };
        }.start();
    }

    private ArrayList<BuoyOverlayItem> getBuoyOverlayItems(ArrayList<BuoyData> buoyDataList) {
        ArrayList<BuoyOverlayItem> buoyOverylayItemList = new ArrayList<BuoyOverlayItem>();
        for (BuoyData bd : buoyDataList) {
            if (bd.geoRssPoint != null) {
                GeoPoint gp = LocationHelper.getGeoPoint(bd.geoRssPoint);
                if (gp != null) {
                    BuoyOverlayItem boi = new BuoyOverlayItem(gp, bd);
                    buoyOverylayItemList.add(boi);
                } else {
                    Log.w(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG
                        + "  buoy WITH incomplete geoRssPoint data skipped - " + bd.title);
                }
            } else {
                Log.w(Constants.LOGTAG, " " + MapViewActivity.CLASSTAG + "  buoy WITHOUT geoRssPoint data skipped - "
                    + bd.title);
            }
        }
        return buoyOverylayItemList;
    }
}
