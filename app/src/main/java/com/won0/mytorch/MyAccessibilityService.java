package com.won0.mytorch;

import android.accessibilityservice.AccessibilityButtonController;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class MyAccessibilityService extends AccessibilityService {
    private String TAG = "MYTEST";
    private AccessibilityButtonController accessibilityButtonController;
    private AccessibilityButtonController
            .AccessibilityButtonCallback accessibilityButtonCallback;
    private boolean mIsAccessibilityButtonAvailable;

    public MyAccessibilityService() {
    }
    public void disable()
    {
        this.disableSelf();
    }
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent(): 실행");
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "onInterrupt(): 실행");
    }


    @Override
    protected void onServiceConnected() {
        Log.d(TAG, "onServiceConnected()");
        accessibilityButtonController = getAccessibilityButtonController();
        mIsAccessibilityButtonAvailable =
                accessibilityButtonController.isAccessibilityButtonAvailable();
        TorchManager.switchFlashLight(getApplicationContext(), true);

        ScreenActionReceiver screenactionreceiver = new ScreenActionReceiver();
        screenactionreceiver.setAccessibilityService(this);
        registerReceiver(screenactionreceiver, screenactionreceiver.getFilter());



        if (!mIsAccessibilityButtonAvailable) {
            return;
        }
        Log.d(TAG, "onServiceConnected().2");
        AccessibilityServiceInfo serviceInfo = getServiceInfo();
        serviceInfo.flags
                |= AccessibilityServiceInfo.FLAG_REQUEST_ACCESSIBILITY_BUTTON;
        setServiceInfo(serviceInfo);

        accessibilityButtonCallback =
                new AccessibilityButtonController.AccessibilityButtonCallback() {
                    @Override
                    public void onClicked(AccessibilityButtonController controller) {
                        Log.d(TAG, "Accessibility button pressed!");

                        // Add custom logic for a service to react to the
                        // accessibility button being pressed.
                    }

                    @Override
                    public void onAvailabilityChanged(
                            AccessibilityButtonController controller, boolean available) {
                        if (controller.equals(accessibilityButtonController)) {
                            mIsAccessibilityButtonAvailable = available;
                        }
                        Log.d(TAG, "onAvailabilityChanged()");
                    }
                };

        if (accessibilityButtonCallback != null) {
            accessibilityButtonController.registerAccessibilityButtonCallback(
                    accessibilityButtonCallback, null);
        }
    }
}
