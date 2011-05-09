package com.holmes;

import android.content.Context;

import com.holmes.map.AccelerometerData;
import com.holmes.map.GPSData;
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
}
