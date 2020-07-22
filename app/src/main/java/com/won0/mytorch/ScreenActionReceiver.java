package com.won0.mytorch;

import android.accessibilityservice.AccessibilityService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public class ScreenActionReceiver extends BroadcastReceiver {
    private MyAccessibilityService myAccessibilityService;
    private String TAG = "MYTEST";

    @Override
    public void onReceive(Context context, Intent intent) {


        String action = intent.getAction();


        if(Intent.ACTION_SCREEN_ON.equals(action))
        {
            Log.d(TAG, "screen is on...");

        }

        else if(Intent.ACTION_SCREEN_OFF.equals(action))
        {
            Log.d(TAG, "screen is off...");
            myAccessibilityService.unregisterReceiver(this);
            myAccessibilityService.disable();
            TorchManager.switchFlashLight(myAccessibilityService.getApplicationContext(), false);
        }

        else if(Intent.ACTION_USER_PRESENT.equals(action))
        {
            Log.d(TAG, "screen is unlock...");
        }

    }

    public IntentFilter getFilter(){
        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        return filter;
    }

    public void setAccessibilityService(MyAccessibilityService myAccessibilityService) {
        this.myAccessibilityService = myAccessibilityService;
    }
}