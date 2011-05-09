package com.holmes.map.gps;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

public class GPSData {
  private ArrayList<GeoPoint> mGeoPoints = null;
  
  public GPSData() {
    mGeoPoints = new ArrayList<GeoPoint>();
  }
  
  public ArrayList<GeoPoint> getPoints() {
    return mGeoPoints;
  }
  
  public void addPoint(GeoPoint point) {
    mGeoPoints.add(point);
  }
  
  public GeoPoint getPoint(int index) {
    if (mGeoPoints == null || mGeoPoints.size() < index) {
      return null;    
    }
    return mGeoPoints.get(index);
  }
  
  public int getCount() {
    return mGeoPoints.size();
  }
}