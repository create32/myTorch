package com.won0.mytorch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MYTEST";
    private CameraManager mCameraManager;
    private String mCameraId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity.onCreate(): 실행");

        boolean isFlashAvailable = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (isFlashAvailable) {
            Log.d(TAG, "MainActivity.onCreate(): 카메라 확인");
            mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            try {
                mCameraId = mCameraManager.getCameraIdList()[0];
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        Button buttonEnable = findViewById(R.id.buttonEnable);
        buttonEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TorchManager.switchFlashLight(getApplicationContext(), true);
            }
        });


    }

}
