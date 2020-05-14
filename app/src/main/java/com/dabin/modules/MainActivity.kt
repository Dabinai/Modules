package com.dabin.modules

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cdv_time.setTime(5)
        cdv_time.start()
        cdv_time.setLoadingFinishListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (cdv_time != null && cdv_time.isShown()) {
            cdv_time.stop()
        }
    }
}
