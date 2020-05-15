package com.dabin.module_home;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dabin.library_base.fragment.MvvmLazyFragment;
import com.dabin.library_base.viewmodel.IMvvmBaseViewModel;
import com.dabin.library_common.router.RouterFragmentPath;
import com.dabin.module_home.databinding.HomeFragmentHomeBinding;

@Route(path = RouterFragmentPath.Home.PAGER_HOME)
public class HomeFragment extends MvvmLazyFragment<HomeFragmentHomeBinding, IMvvmBaseViewModel> {

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initView();
    }

    private void initView() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.home_fragment_home;
    }

    @Override
    protected IMvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    protected void onRetryBtnClick() {

    }
}
