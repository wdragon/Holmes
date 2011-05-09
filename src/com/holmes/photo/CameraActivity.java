package com.holmes.photo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.holmes.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {
  private final static String TAG = CameraActivity.class.getName();
  private final static boolean DEBUG = true;

  private final static String START_CAMERA = "Start Camera";

  private Preview mPreview;
  private Listener mListener;

  public static interface Listener {
    public void onPhoto(PhotoData photo);
  }

  public static void takePhotos(Context context, Listener listener) {
    Intent startCamera = new Intent(START_CAMERA);
    startCamera.setClass(context, CameraActivity.class);
    context.startActivity(startCamera);
  }

  ShutterCallback mShutterCB = new ShutterCallback() {
    @Override
    public void onShutter() {
      if (DEBUG) {
        Log.v(TAG, "onShutter");
      }
    }
  };

  PictureCallback mRawCB = new PictureCallback() {
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
      if (DEBUG) {
        Log.v(TAG, "raw image onPictureTaken");
      }
    }
  };

  PictureCallback mJpegCB = new PictureCallback() {
    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
      FileOutputStream out = null;
      try {
        String filename = String.format("/sdcard/%d.jpg", System.currentTimeMillis());
        out = new FileOutputStream(filename);
        out.write(data);
        out.close();
        PhotoData photo = new PhotoData();
        photo.fileName = filename;
        mListener.onPhoto(photo);
      } catch (FileNotFoundException e) {
        Log.e("TestCamera", e.getMessage());
      } catch (IOException e) {
        Log.e("TestCamera", e.getMessage());
      }
    }
  };

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.camera);
    mPreview = new Preview(this);
    ((FrameLayout)findViewById(R.id.preview)).addView(mPreview);

    Button shot = (Button)findViewById(R.id.ShotButton);
    shot.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        mPreview.getCamera().takePicture(mShutterCB, mRawCB, mJpegCB);
      }
    });
  }
}
