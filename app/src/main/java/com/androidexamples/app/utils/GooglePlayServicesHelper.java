package com.androidexamples.app.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by marcos on 17/01/2017.
 */

public class GooglePlayServicesHelper implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private Context context;

    /**
     * GPS Speed:
     * https://developers.google.com/android/reference/com/google/android/gms/location/LocationRequest
     */
    public static int LOCATION_GPS_PRIORITY = LocationRequest.PRIORITY_HIGH_ACCURACY;
    public static int LOCATION_GPS_INTERVAL_MILLIS = 60000;//60 seg
    public static int LOCATION_GPS_FASTEST_INTERVAL_MILLIS = 30000;//30seg

    private final GoogleApiClient mGoogleApiClient;
    private Set<LocationListener> locationListeners;
    private boolean gpsOn;

    public GooglePlayServicesHelper(Context context, boolean gpsOn) {
        this.gpsOn = gpsOn;

        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this);

        if (gpsOn)
            builder.addApi(LocationServices.API);

        mGoogleApiClient = builder.build();
        this.context = context;
    }

    // Conecta no Google Play Services
    public void onResume(LocationListener locationListener) {
        mGoogleApiClient.connect();

        if (gpsOn) {
            addLocationListeners(locationListener);
        }
    }

    // Desconecta do Google Play Services
    public void onPause() {
        if (mGoogleApiClient.isConnected()) {
            if (gpsOn) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            }
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    @RequiresPermission(value = Manifest.permission.ACCESS_COARSE_LOCATION)
    public void onConnected(Bundle bundle) {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(LOCATION_GPS_INTERVAL_MILLIS); // 10 segundos
        mLocationRequest.setFastestInterval(LOCATION_GPS_FASTEST_INTERVAL_MILLIS); // 5 segundos
        mLocationRequest.setPriority(LOCATION_GPS_PRIORITY);

        if (gpsOn) {
            // Start GPS
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                return;
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int status) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void addLocationListeners(LocationListener locationListeners) {
        if (this.locationListeners == null) {
            this.locationListeners = new LinkedHashSet<>();
        }
        this.locationListeners.add(locationListeners);
    }

    public String getLastLocationString() {
        Location l = getLastLocation();
        if (l != null) {
            double latitude = l.getLatitude();
            double longitude = l.getLongitude();
            return String.format("lat/lng: %s/%s", latitude, longitude);
        } else {
            return "lat/lng: 0/0";
        }

    }

    /**
     *
     * */
    public Location getLastLocation() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return null;

        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        return location;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (locationListeners != null) {
            for (LocationListener listener : locationListeners) {
                listener.onLocationChanged(location);
            }
        }
    }
}
