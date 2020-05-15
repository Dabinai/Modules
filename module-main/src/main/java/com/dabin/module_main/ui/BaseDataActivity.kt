package com.dabin.module_main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

abstract class BaseDataActivity : AppCompatActivity() {

    protected var mDrawList : MutableList<Int> = ArrayList();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData();

    }

    private fun initData(){
        for (i in 0..2){
            val identifier = resources.getIdentifier("guide$i", "drawable", packageName)
            mDrawList.add(identifier)
        }
    }
}