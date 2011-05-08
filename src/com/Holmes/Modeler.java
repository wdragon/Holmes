package com.Holmes;

public class Modeler {
	
	protected Configuration config;
	
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
