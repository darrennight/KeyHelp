package com.hao.keyhelp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import com.hao.keyhelp.other.FloatBallService;
import com.hao.keyhelp.view.KeyHelpAccService;
import com.hao.keyhelp.view.MyWindowManager;

public class MainActivity extends AppCompatActivity {

    private ImageView mMenu;
    private MyWindowManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMenu = ((ImageView) findViewById(R.id.iv_menu));
        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showPopControl();
                /*Intent intent = new Intent(MainActivity.this, FloatBallService.class);
                Bundle bundle = new Bundle();
                bundle.putInt("type", FloatBallService.TYPE_ADD);
                intent.putExtras(bundle);
                startService(intent);*/

                Intent intent = new Intent(MainActivity.this,KeyHelpAccService.class);
                startService(intent);

            }
        });
    }


    private void showPopControl(){
        /*if(manager==null){
            manager = MyWindowManager.getInstance();
            manager.createWindow(getApplicationContext());
        }*/
        Intent intent = new Intent(MainActivity.this,KeyHelpAccService.class);
        startService(intent);

    }



    /**
     * 按键正常进入动画
     */
    public void startAnimationsIn() {

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mMenu, "translationX", -200);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mMenu, "translationY", -200);


        //oa.setDuration(200);
        //oa.setInterpolator(new BounceInterpolator());
        //oa.start();



        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.setDuration(200);
        animSet.setInterpolator(new BounceInterpolator());
        animSet.start();
    }
}
