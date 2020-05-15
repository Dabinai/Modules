package com.dabin.module_main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.dabin.library_base.storage.MmkvHelper;
import com.dabin.library_common.adapter.ScreenAutoAdapter;
import com.dabin.module_main.R;
import com.dabin.module_main.ui.GuideActivity;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

public class SplashActivity extends AppCompatActivity {


    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenAutoAdapter.match(this, 375.0f);
        setContentView(R.layout.main_activity_splash);

        ImmersionBar.with(this)
                .titleBar(findViewById(R.id.top_view))
                .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
                .init();

        mHandler.postDelayed(this::startToMain, 3000);
    }

    private void startToMain() {
        if (MmkvHelper.getInstance().getMmkv().decodeBool("first",true)){
            startActivity(new Intent(this, GuideActivity.class));
        }else {
//            MainActivity.start(this);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
