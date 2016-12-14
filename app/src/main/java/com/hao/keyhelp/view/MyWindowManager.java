package com.hao.keyhelp.view;

import android.accessibilityservice.AccessibilityService;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.hao.keyhelp.R;
import com.hao.keyhelp.other.FloatBallService;
import com.hao.keyhelp.other.FloatBallView;

public class MyWindowManager {

    private MyWindowManager(){};
    private static MyWindowManager mInstance = new MyWindowManager();
    public static MyWindowManager  getInstance(){
        if(mInstance == null){
            mInstance = new MyWindowManager();
        }
        return mInstance;
    }
    private float mLastDownX;
    private float mLastDownY;
    private ImageView mMenu;
    private ImageView mHome;
    private ImageView mRecent;
    private static LinearLayout mLayoutWindow;
    private WindowManager.LayoutParams param;
  
    /**
     * 用于控制在屏幕上添加或移除悬浮窗 
     */  
    private static WindowManager mWindowManager;

    public  void createWindow(final Context context) {

        WindowManager windowManager = getWindowManager(context);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();  
        int screenHeight = windowManager.getDefaultDisplay().getHeight();  
        if (mLayoutWindow == null) {
            mLayoutWindow = ((LinearLayout) View.inflate(context, R.layout.window_activity_name, null));

            mMenu = ((ImageView) mLayoutWindow.findViewById(R.id.iv_pop_menu));
            mHome = ((ImageView) mLayoutWindow.findViewById(R.id.iv_pop_home));
            mRecent = ((ImageView) mLayoutWindow.findViewById(R.id.iv_pop_recent));


             param=new WindowManager.LayoutParams();
            //7.1.1系统做了修改TYPE_TOAST 只能显示Toast时长就消失
            param.type=WindowManager.LayoutParams.TYPE_TOAST;
            //param.type= WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            param.format=1;
            //param.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; // 不能抢占聚焦点
            //param.flags = param.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
            //param.flags = param.flags | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            ////param.flags = param.flags | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
            //param.flags = param.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; // 排版不受限制

            param.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            //param.alpha = 1.0f;
            param.format= PixelFormat.RGBA_8888;

            param.gravity= Gravity.RIGHT|Gravity.CENTER_VERTICAL;   //调整悬浮窗口至左上角
            //param.gravity= Gravity.LEFT | Gravity.TOP;   //调整悬浮窗口至左上角
            //以屏幕左上角为原点，设置x、y初始值
            //param.x=0;
            //param.y=0;

            param.x = 0;
            param.y = 0;

            //设置悬浮窗口长宽数据
            //param.width=WindowManager.LayoutParams.WRAP_CONTENT;
            //param.height=WindowManager.LayoutParams.WRAP_CONTENT;

            param.width=200;
            param.height=600;


            mMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KeyHelpAccService service = ((KeyHelpAccService) context);
                    service.performBackClick();
                    //KeyHelpAccService.getInstance().performBackClick();
                    //new Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);//悬浮窗也关闭了
                }
            });

            mHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KeyHelpAccService service = ((KeyHelpAccService) context);
                    service.performHome();
                }
            });
            mRecent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KeyHelpAccService service = ((KeyHelpAccService) context);
                    service.performRecent();
                    //testMove();
                }
            });
            /*mRecent.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            mLastDownX = event.getX();
                            mLastDownY = event.getY();
                            break;

                        case MotionEvent.ACTION_MOVE:
                            param.x = (int) (Math.abs(event.getRawX() - mLastDownX));//距离边界都有一定的距离
                            param.y = (int) (Math.abs(event.getRawY() - mLastDownY));
                            param.gravity= Gravity.LEFT | Gravity.TOP;
                            mWindowManager.updateViewLayout(mLayoutWindow, param);
                            break;
                    }
                    return true;
                }
            });*/
            windowManager.addView(mLayoutWindow, param);
        }
    }

    private static WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }


    private void testMove(){
        param.width=400;
        mWindowManager.updateViewLayout(mLayoutWindow, param);

        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mMenu, "translationX", -100);


        anim2.setDuration(200);
        anim2.setInterpolator(new BounceInterpolator());
        anim2.start();
    }

  
}  