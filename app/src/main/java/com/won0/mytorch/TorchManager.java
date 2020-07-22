package com.won0.mytorch;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.util.Log;

public class TorchManager {
    static
    public void switchFlashLight(Context context, boolean status) {
        CameraManager mCameraManager = null;
        String mCameraId = null;
        boolean isFlashAvailable = context.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (isFlashAvailable) {
            Log.d("MYTEST", "TorchManager.onCreate(): 카메라 확인");
            mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            try {
                mCameraId = mCameraManager.getCameraIdList()[0];
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        try {
            Log.d("MYTEST", "TorchManager.switchFlashLight(): 플래시");
            mCameraManager.setTorchMode(mCameraId, status);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
