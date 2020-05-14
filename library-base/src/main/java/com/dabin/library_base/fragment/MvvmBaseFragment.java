package com.dabin.library_base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.dabin.library_base.activity.IBaseView;
import com.dabin.library_base.loadsir.EmptyCallback;
import com.dabin.library_base.loadsir.ErrorCallback;
import com.dabin.library_base.loadsir.LoadingCallback;
import com.dabin.library_base.utils.ToastUtil;
import com.dabin.library_base.viewmodel.IMvvmBaseViewModel;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

public abstract class MvvmBaseFragment<V extends ViewDataBinding, VM extends IMvvmBaseViewModel>
        extends Fragment implements IBaseView {

    protected V viewDataBinding;
    protected VM viewModel;


    protected String mFragmentTag = this.getClass().getSimpleName();

    private LoadService mLoadService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParameters();
        Log.d(mFragmentTag, "onCreate");
    }

    private void initParameters() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        Log.d(mFragmentTag, " : onCreateView");
        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = getViewModel();
        if (null != viewModel) {
            viewModel.attachUi(this);
        }
        if (getBindingVariable() > 0) {
            viewDataBinding.setVariable(getBindingVariable(), viewModel);
            viewDataBinding.executePendingBindings();
        }
        Log.d(mFragmentTag, " : onViewCreated");


    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        Log.d(mFragmentTag, " : onAttach");

    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(mFragmentTag, " : onResume");

    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(mFragmentTag, " : onPause");

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        Log.d(mFragmentTag, " : onDestroyView");

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (null != viewModel && viewModel.isUiAttach())
        {
            viewModel.detachUi();
        }
        Log.d(mFragmentTag, " : onDestroy");

    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        if (null != viewModel && viewModel.isUiAttach())
        {
            viewModel.detachUi();
        }
        Log.d(mFragmentTag, " : onDetach");

    }


    private boolean isShowedContent = false;

    @Override
    public void showContent()
    {
        if (null != mLoadService)
        {
            isShowedContent = true;
            mLoadService.showSuccess();
        }
    }

    @Override
    public void showLoading()
    {
        if (null != mLoadService)
        {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void showEmpty()
    {
        if (null != mLoadService)
        {
            mLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void showFailure(String message)
    {
        if (null != mLoadService)
        {
            if (!isShowedContent)
            {
                mLoadService.showCallback(ErrorCallback.class);
            }
            else
            {
                ToastUtil.show(getContext(), message);
            }
        }
    }

    public void setLoadSir(View view)
    {
        mLoadService = LoadSir.getDefault()
                .register(view, (Callback.OnReloadListener) v -> onRetryBtnClick());
    }

    @LayoutRes
    public abstract int getLayoutId();

    /**
     * 获取ViewModel
     */
    protected abstract VM getViewModel();

    /**
     * 获取参数
     */
    public abstract int getBindingVariable();

    /**
     * 失败重试,加载事件
     */
    protected abstract void onRetryBtnClick();
}
