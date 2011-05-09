package com.holmes.map.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapGPSLocationOverlay extends Overlay {
	private Context mContext;
	private GeoPoint mGeoPoint = null;
	
	public MapGPSLocationOverlay(Context context, GeoPoint geo_point) {
		mContext = context;
		mGeoPoint = geo_point;
	}
	
	public void updateLocation(GeoPoint geo_point) {
		mGeoPoint = geo_point;
	}
	
	@Override
	public boolean draw (Canvas canvas, MapView mapView, boolean shadow, long when) {
		if (mGeoPoint != null) {
			return super.draw(canvas, mapView, shadow, when);
		}
		Projection projection = mapView.getProjection();
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.GREEN);
		
		Point point = new Point();
		projection.toPixels(mGeoPoint, point);
		
        paint.setAlpha(50);
        canvas.drawCircle(point.x, point.y, 3, paint);
		return super.draw(canvas, mapView, shadow, when);
	}
}
