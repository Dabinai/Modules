package com.dabin.library_base.model;

import java.lang.ref.WeakReference;
/**
 * 应用模块: model
 * <p>
 * 类描述: 通用的基类model
 * <p>
 *
 */
public abstract class BaseModel<T> extends SuperBaseModel<T> {

    public void loadSuccess(T data) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakListener : mWeakReferenceDeque) {
                    if (weakListener.get() instanceof IModelListener) {
                        IModelListener iModelListener = (IModelListener) weakListener.get();
                        if (null != iModelListener) {
                            iModelListener.onLoadFinish(BaseModel.this,data);
                        }
                    }
                }


            }, 0);
        }
    }

    public void loadFail(String prompt) {
        synchronized (this) {
            mUiHandler.postDelayed(() -> {
                for (WeakReference<IBaseModelListener> weakListener : mWeakReferenceDeque) {
                    if (weakListener.get() instanceof IModelListener) {
                        IModelListener iModelListener = (IModelListener) weakListener.get();
                        if (null != iModelListener) {
                            iModelListener.onLoadFail(BaseModel.this,prompt);
                        }
                    }
                }


            }, 0);
        }
    }

    @Override
    protected void notifyCacheData(T data) {
        loadSuccess(data);
    }
}
