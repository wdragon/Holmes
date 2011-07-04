package com.holmes.map.overlay;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

public class MapTracksOverlay extends Overlay {
  private Context mContext;
  private ArrayList<GeoPoint> mTrackGeoPoints = null;

  public MapTracksOverlay(Context context, ArrayList<GeoPoint> gps) {
    super();
    mContext = context;
    mTrackGeoPoints = gps;
  }

  @Override
  public boolean draw (Canvas canvas, MapView mapView, boolean shadow, long when) {
    if (mTrackGeoPoints == null && mTrackGeoPoints.size() <= 1) {
      return super.draw(canvas, mapView, shadow, when); 
    }
  
    Projection projection = mapView.getProjection();
    Paint paint = new Paint();
    paint.setAntiAlias(true);
    paint.setColor(Color.RED);
    paint.setStrokeWidth(3);
    paint.setAlpha(50);
      
    Point last_point = new Point();
    projection.toPixels(mTrackGeoPoints.get(0), last_point);
    for (int i = 1; i< mTrackGeoPoints.size(); i++) {
      Point point = new Point();
      projection.toPixels(mTrackGeoPoints.get(i), point);
      canvas.drawLine(last_point.x, last_point.y, point.x,point.y, paint);      
    }      
    return super.draw(canvas, mapView, shadow, when);
  }    
}