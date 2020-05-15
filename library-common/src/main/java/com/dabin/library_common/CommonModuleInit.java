package com.dabin.library_common;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dabin.library_base.base.BaseApplication;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;

public class CommonModuleInit implements  IModuleInit{
    @Override
    public boolean onInitAhead(BaseApplication application) {
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return application.issDebug();
            }
        });
        if(application.issDebug()){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(application);
        MMKV.initialize(application);
        Logger.i("基础层初始化完毕 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(BaseApplication application) {
        return false;
    }
}
