package com.dabin.library_common.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.dabin.library_common.router.RouterActivityPath;
import com.orhanobut.logger.Logger;

public class LoginInterceptor implements IInterceptor {
    private Context context;
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if(postcard.getPath().equals(RouterActivityPath.User.PAGER_ATTENTION)){

        }
    }

    @Override
    public void init(Context context) {
        this.context = context;
        Logger.d("登录拦截器被初始化了");
    }
}
