package com.dabin.library_base.viewmodel;

public interface IMvvmBaseViewModel<V> {
    /**
     * 关联View
     * */
    void attachUi(V view);

    /**
     * 获取View
     * */
    V getPageView();

    /**
     * 是否已经关联View
     * */
    boolean isUiAttach();

    /**
     * 解除关联
     * */
    void detachUi();
}
