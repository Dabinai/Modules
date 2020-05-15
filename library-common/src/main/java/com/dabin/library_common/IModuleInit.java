package com.dabin.library_common;

import com.dabin.library_base.base.BaseApplication;

public interface IModuleInit {
    /** 需要优先初始化的 */
    boolean onInitAhead(BaseApplication application);

    /** 可以后初始化的 */
    boolean onInitLow(BaseApplication application);
}
