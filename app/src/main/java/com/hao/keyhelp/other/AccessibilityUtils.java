package com.hao.keyhelp.other;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import com.hao.keyhelp.R;

public class AccessibilityUtils {
    public static final String TAG = "tag";
    private static boolean isWhite=true;
 
    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnable = 0;
        try { 
            accessibilityEnable = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        } 
 
        if (accessibilityEnable == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            Log.e(TAG, " servicesList=" + services);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            } 
        } 
 
        return false; 
    } 
 
    /** 
     * 单击返回功能 
     * 
     * @param ballService 
     */ 
    public static void doBack(FloatBallService ballService) {
        ballService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);
    } 
 
    /** 
     * 左右滑动打开多任务 
     * 
     * @param 
     */ 
    public static void doPullRight(FloatBallService ballService) {
        ballService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS);
    } 
 
    /** 
     * 上拉返回桌面 
     * 
     * @param 
     */ 
    public static void doPullUp(FloatBallService ballService) {
        ballService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME);
    } 
 
    /** 
     * 下拉打开通知栏 
     * 
     * @param 
     */ 
    public static void doPullDown(FloatBallService ballService) {
        ballService.performGlobalAction(AccessibilityService.GLOBAL_ACTION_NOTIFICATIONS);
    } 
 
    /** 
     * 左右滑动打开多任务 
     *  @param 
     * @param service 
     * @param smallIv 
     */ 
    public static void doPullLeft(FloatBallService service, ImageView smallIv,ImageView bigIv) {
        if(isWhite){
            smallIv.setBackgroundResource(R.drawable.icon_1);
            bigIv.setBackgroundResource(R.drawable.icon_1);
            isWhite=false;
        }else{ 
            smallIv.setBackgroundResource(R.drawable.icon_1);
            bigIv.setBackgroundResource(R.drawable.icon_1);
            isWhite=true;
        } 
 
 
    } 
} 
