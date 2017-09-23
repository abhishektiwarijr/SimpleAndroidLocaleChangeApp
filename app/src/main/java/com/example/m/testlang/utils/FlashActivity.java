package com.example.m.testlang.utils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.m.testlang.R;

/**
 * Created by Abhishek Tiwari on 22.09.2017.
 **/
public class FlashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        overridePendingTransition(R.anim.animation_localization_activity_transition_in,R.anim.animation_localization_activity_transition_out);
        setContentView(R.layout.activity_blank_dummy);
        delayedFinish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.animation_localization_activity_transition_in,R.anim.animation_localization_activity_transition_out);
    }

    private void delayedFinish() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 200);
    }
}