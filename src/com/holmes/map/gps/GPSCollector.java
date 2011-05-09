package com.holmes.map.gps;

import java.text.Format;
import java.text.SimpleDateFormat;
 
 
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
 
 
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
 
public class GPSCollector {
	private Context mContext;
	private LocationListener mListener = null;
	private LocationManager mLocationManager = null;
	private Location mLocation = null;

	private boolean gpsEnabled = false;
	
	public GPSCollector(Context context, LocationListener listener) {
		mContext = context;
		mListener = listener;
	}
	
	public boolean isGPSEnabled() {
		return gpsEnabled;	
	}
	 
	public boolean startGPSCollect() {
		if (mListener == null) {		
			return false;
		}
		mLocationManager = (LocationManager) mContext.getSystemService(Activity.LOCATION_SERVICE);		
		mLocationManager.requestLocationUpdates(mLocationManager.GPS_PROVIDER, 50, 10, mListener);	
		return true;
	}
	
	public String getGeoLocation() {
	    if (mLocation == null) {
	    	return "UNKNOWN";
	    }
	    Format formatter = new SimpleDateFormat("hh:mm:ss");           
	    return String.format("Time:%s Lat:%f Lon:%f", formatter.format(mLocation.getTime()), mLocation.getLatitude(), mLocation.getLongitude());
	}	 
}
