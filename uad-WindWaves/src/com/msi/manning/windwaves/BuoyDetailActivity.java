package com.msi.manning.windwaves;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.msi.manning.windwaves.data.BuoyData;

// ENHANCE build charts from the realtime data (http://www.ndbc.noaa.gov/data/realtime2/41002.txt)
// ENHANCE hook in with other NOAA data - http://www.weather.gov/rss/

/**
 * BuoyDetail Activity for WindWaves.
 * 
 * @author charliecollins
 */
public class BuoyDetailActivity extends Activity {

    private static final String CLASSTAG = BuoyDetailActivity.class.getSimpleName();

    public static final String BUOY_DETAIL_URL = "http://www.ndbc.noaa.gov/station_page.php?station=";

    public static BuoyData buoyData = null;

    private Button buttonWeb;
    private TextView title;
    private TextView location;
    private TextView date;
    private TextView airTemp;
    private TextView waterTemp;
    private TextView atmoPress;
    private TextView atmoTend;
    private TextView windDir;
    private TextView windSpeed;
    private TextView windGust;
    private TextView waveHeight;
    private TextView wavePeriod;

    @Override
    public void onCreate(final Bundle icicle) {
        super.onCreate(icicle);
        Log.v(Constants.LOGTAG, " " + BuoyDetailActivity.CLASSTAG + " onCreate");

        setContentView(R.layout.buoydetail_activity);

        buttonWeb = (Button) findViewById(R.id.button_web);
        title = (TextView) findViewById(R.id.bd_title);
        location = (TextView) findViewById(R.id.bd_location);
        date = (TextView) findViewById(R.id.bd_date);
        airTemp = (TextView) findViewById(R.id.bd_air_temp);
        waterTemp = (TextView) findViewById(R.id.bd_water_temp);
        atmoPress = (TextView) findViewById(R.id.bd_atmo_press);
        atmoTend = (TextView) findViewById(R.id.bd_atmo_tend);
        windDir = (TextView) findViewById(R.id.bd_wind_dir);
        windSpeed = (TextView) findViewById(R.id.bd_wind_speed);
        windGust = (TextView) findViewById(R.id.bd_wind_gust);
        waveHeight = (TextView) findViewById(R.id.bd_wave_height);
        wavePeriod = (TextView) findViewById(R.id.bd_wave_period);

        title.setText(BuoyDetailActivity.buoyData.title);
        location.setText("Location:" + BuoyDetailActivity.buoyData.location);
        date.setText("Data Poll Date: "
            + (BuoyDetailActivity.buoyData.dateString != null ? BuoyDetailActivity.buoyData.dateString : "NA"));
        airTemp.setText("Air Temp: "
            + (BuoyDetailActivity.buoyData.airTemp != null ? BuoyDetailActivity.buoyData.airTemp : "NA"));
        waterTemp.setText("Water Temp: "
            + (BuoyDetailActivity.buoyData.waterTemp != null ? BuoyDetailActivity.buoyData.waterTemp : "NA"));
        atmoPress.setText("Barometric Press: "
            + (BuoyDetailActivity.buoyData.atmoPressure != null ? BuoyDetailActivity.buoyData.atmoPressure : "NA"));
        atmoTend
            .setText("Barometric Trend: "
                + (BuoyDetailActivity.buoyData.atmoPressureTendency != null ? BuoyDetailActivity.buoyData.atmoPressureTendency
                    : "NA"));
        windDir.setText("Wind Direction: "
            + (BuoyDetailActivity.buoyData.windDirection != null ? BuoyDetailActivity.buoyData.windDirection : "NA"));
        windSpeed.setText("Wind Speed: "
            + (BuoyDetailActivity.buoyData.windSpeed != null ? BuoyDetailActivity.buoyData.windSpeed : "NA"));
        windGust.setText("Wind Gust: "
            + (BuoyDetailActivity.buoyData.windGust != null ? BuoyDetailActivity.buoyData.windGust : "NA"));
        waveHeight.setText("Wave Height: "
            + (BuoyDetailActivity.buoyData.waveHeight != null ? BuoyDetailActivity.buoyData.waveHeight : "NA"));
        wavePeriod.setText("Wave Period: "
            + (BuoyDetailActivity.buoyData.wavePeriod != null ? BuoyDetailActivity.buoyData.wavePeriod : "NA"));

        buttonWeb.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(BuoyDetailActivity.buoyData.link)));
            };
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(Constants.LOGTAG, " " + BuoyDetailActivity.CLASSTAG + " onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
