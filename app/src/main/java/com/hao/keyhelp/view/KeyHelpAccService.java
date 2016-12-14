package com.hao.keyhelp.view;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

/**
 *
 * @author zenghao
 * @since 16/12/12 下午3:08
 */
public class KeyHelpAccService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }
    private MyWindowManager manager;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(manager==null){
            manager = MyWindowManager.getInstance();
            manager.createWindow(this);
        }
        return super.onStartCommand(intent, flags, startId);
    }


    public void performBackClick() {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);

    }


    public void performHome() {
        performGlobalAction(GLOBAL_ACTION_HOME);

    }

    /**
     * 魅族屏蔽了此功能
     */
    public void performRecent() {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);

    }
}
