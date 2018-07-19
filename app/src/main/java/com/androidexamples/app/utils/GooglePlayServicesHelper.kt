package com.androidexamples.app.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.annotation.RequiresPermission
import android.support.v4.app.ActivityCompat
import android.util.Log

import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

import java.util.LinkedHashSet

/**
 * Created by marcos on 17/01/2017.
 */

class GooglePlayServicesHelper(private val context: Context, private val gpsOn: Boolean) : GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private val mGoogleApiClient: GoogleApiClient
    private var locationListeners: MutableSet<LocationListener>? = null

    val lastLocationString: String
        get() {
            val l = lastLocation
            if (l != null) {
                val latitude = l.latitude
                val longitude = l.longitude
                return String.format("lat/lng: %s/%s", latitude, longitude)
            } else {
                return "lat/lng: 0/0"
            }

        }

    /**
     *
     */
    val lastLocation: Location?
        get() = if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) null else LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)

    init {

        val builder = GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)

        if (gpsOn)
            builder.addApi(LocationServices.API)

        mGoogleApiClient = builder.build()
    }

    // Conecta no Google Play Services
    fun onResume(locationListener: LocationListener) {
        mGoogleApiClient.connect()

        if (gpsOn) {
            addLocationListeners(locationListener)
        }
    }

    // Desconecta do Google Play Services
    fun onPause() {
        if (mGoogleApiClient.isConnected) {
            if (gpsOn) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this)
            }
            mGoogleApiClient.disconnect()
        }
    }

    @RequiresPermission(value = Manifest.permission.ACCESS_COARSE_LOCATION)
    override fun onConnected(bundle: Bundle?) {
        val mLocationRequest = LocationRequest()
        mLocationRequest.interval = LOCATION_GPS_INTERVAL_MILLIS.toLong() // 10 segundos
        mLocationRequest.fastestInterval = LOCATION_GPS_FASTEST_INTERVAL_MILLIS.toLong() // 5 segundos
        mLocationRequest.priority = LOCATION_GPS_PRIORITY

        if (gpsOn) {
            // Start GPS
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                return
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this)
        }
    }

    override fun onConnectionSuspended(status: Int) {

    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {

    }

    private fun addLocationListeners(locationListeners: LocationListener) {
        if (this.locationListeners == null) {
            this.locationListeners = LinkedHashSet()
        }
        this.locationListeners!!.add(locationListeners)
    }

    override fun onLocationChanged(location: Location) {
        if (locationListeners != null) {
            for (listener in locationListeners!!) {
                listener.onLocationChanged(location)
            }
        }
    }

    companion object {

        /**
         * GPS Speed:
         * https://developers.google.com/android/reference/com/google/android/gms/location/LocationRequest
         */
        var LOCATION_GPS_PRIORITY = LocationRequest.PRIORITY_HIGH_ACCURACY
        var LOCATION_GPS_INTERVAL_MILLIS = 60000//60 seg
        var LOCATION_GPS_FASTEST_INTERVAL_MILLIS = 30000//30seg
    }
}
