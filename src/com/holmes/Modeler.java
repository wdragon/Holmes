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
import com.holmes.storage.LocalStorage;

public class Modeler {
  
  /**
   * Called by MainActivity at the start of the application.
   */
  public static void createInstance(Context context) {
    modeler = new Modeler(context);
  }
  private static Modeler modeler = null;

  /** 
   * Called everywhere to get the modeler.
   */
  public static Modeler getInstance() {
    return modeler;
  }

  
  private Configuration config;
	private Context context;
	private LocalStorage localStorage;
	
	private Modeler(Context context) {
    this.config = new Configuration();
	  this.context = context;
	  this.localStorage = new LocalStorage(context);
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
			}
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
