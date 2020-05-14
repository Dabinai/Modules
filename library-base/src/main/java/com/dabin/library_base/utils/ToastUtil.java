package com.dabin.library_base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * 应用模块: utils
 * <p>
 * 类描述: toast显示工具类
 * <p>
 *
 */
public class ToastUtil
{
    
    private static Toast mToast;
    
    public static void show(Context context, String msg)
    {
        try
        {
            if (null != context && !TextUtils.isEmpty(msg))
            {
                if (null != mToast)
                {
                    mToast.cancel();
                }
                mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
                mToast.setText(msg);
                mToast.show();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
    }
}