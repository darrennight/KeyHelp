package com.hao.keyhelp.other;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import com.hao.keyhelp.view.MyWindowManager;

public class FloatBallService extends AccessibilityService {
    public static final int TYPE_ADD = 0;
    public static final int TYPE_DEL = 1;
 
    @Override 
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
 
    } 
 
    @Override 
    public void onInterrupt() { 
 
    } 
 
    @Override 
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyWindowManager.getInstance().createWindow(this);
        Bundle extras = intent.getExtras();
        if (null != extras) {
 
            int type = extras.getInt("type");
            if (type == TYPE_ADD) {
                FloatWindowManager.addBallView(this);
            } else { 
                FloatWindowManager.removeBallView(this);
            } 
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void performBackClick() {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);

    }
} 