package com.holmes.photo;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Preview extends SurfaceView implements SurfaceHolder.Callback {
  private static final String TAG = Preview.class.getName();
  private static final boolean DEBUG = true;

  private Camera mCamera;

  public Preview(Context context) {
    super(context);
    if (DEBUG) {
      Log.v(TAG, "Camera preview created");
    }
    SurfaceHolder holder = getHolder();
    holder.addCallback(this);
    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
  }

  public Camera getCamera() {
    return mCamera;
  }

  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width,
                             int height) {
    if (DEBUG) {
      Log.v(TAG, "surfaceChanged");
    }
    Camera.Parameters params = mCamera.getParameters();
    params.setPreviewSize(width, height);
    mCamera.setParameters(params);
    mCamera.startPreview();
  }

  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    if (DEBUG) {
      Log.v(TAG, "surfaceCreated");
    }
    mCamera = Camera.open();
    Camera.Parameters params = mCamera.getParameters();
    params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
    mCamera.setParameters(params);

    try {
      mCamera.setPreviewDisplay(holder);
    } catch (IOException e) {
      Log.e(TAG, e.getMessage());
    }

    mCamera.startPreview();
  }

  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    if (DEBUG) {
      Log.v(TAG, "surfaceDestroyed");
    }
    mCamera.stopPreview();
    mCamera.release();
    mCamera = null;
  }
}
