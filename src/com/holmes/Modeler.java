package com.holmes;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.holmes.map.AccelerometerData;
import com.holmes.map.gps.GPSCollector;
import com.holmes.map.gps.GPSData;
import com.holmes.photo.PhotoData;

public class Modeler {
	protected Configuration config;
	private GPSCollector mGPSCollector;
	private LocationListener mLocationListener = null;
	private Context mContext;
	
	public Modeler(Context context) {
		mContext = context;
		mGPSCollector = new GPSCollector(mContext, getLocationListener());
	}
	
	public boolean startCollectData(Configuration config) {
		mGPSCollector.startGPSCollect();
		return true;		
	}
	
	public boolean stopCollectData() {
		return true;
	}
		
	public Configuration getConfigration() {
		return config;
	}
	
	public boolean storeGPSData(GPSData gps_data) {
		return true;
	}
	
	public GPSData getGPSData(int time_start, int time_end) {
		return null;
	}
	
	public boolean storeAccelerometerData(AccelerometerData acc_data) {
		return true;
	}
	
	public AccelerometerData getAccelerometerData(int time_start, int time_end) {
		return null;
	}
		
	public boolean StorePhotoData(PhotoData photo_data) {
		return true;
	}

	public PhotoData getPhotoData(int time_start, int time_end) {
		return null;
	}
	
	private LocationListener getLocationListener() {
		return new LocationListener() {
			@Override
			public void onLocationChanged(Location loc) {
				Log.d("geo", loc.toString());
				//mLocation = loc;
			}
			@Override
			public void onProviderDisabled(String provider) {
				Log.d("geo", "provider disabled");
				// request turn on gps service
/*					if(!mLocationManager.isProviderEnabled(mLocationManager.GPS_PROVIDER)) {
						//showDialog(0);
						gpsEnabled = false;
					}
*/				}
			@Override
			public void onProviderEnabled(String provider) {
				Log.d("geo", "provider enabled");
				//gpsEnabled = true;
			}
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				Log.d("geo", "status changed");
			}
		};
	}
}
