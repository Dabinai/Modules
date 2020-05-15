package com.dabin.module_main.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ColorUtils;
import com.dabin.library_base.activity.MvvmBaseActivity;
import com.dabin.library_base.storage.MmkvHelper;
import com.dabin.library_base.viewmodel.IMvvmBaseViewModel;
import com.dabin.library_common.adapter.ScreenAutoAdapter;
import com.dabin.library_common.router.RouterActivityPath;
import com.dabin.module_main.R;
import com.dabin.module_main.adapter.MainPageAdapter;
import com.dabin.module_main.databinding.MainActivityGuideBinding;
import com.dabin.module_main.databinding.MainActivityMainBinding;
import com.gyf.immersionbar.ImmersionBar;

import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;

@Route(path = RouterActivityPath.Main.PAGER_MAIN)
public class MainActivity extends MvvmBaseActivity<MainActivityMainBinding,IMvvmBaseViewModel> {

    private List<Fragment> fragments;

    private MainPageAdapter adapter;

    private NavigationController mNavigationController;



    public static void start(Context context){
//        MmkvHelper.getInstance().getMmkv().encode("first",false);
        context.startActivity(new Intent(context,MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ScreenAutoAdapter.match(this, 375.0f);
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .statusBarColor(R.color.main_color_bar)
                .navigationBarColor(R.color.main_color_bar)
                .fitsSystemWindows(true)
                .autoDarkModeEnable(true)
                .init();

        initView();
        initFragment();
    }

    private void initView() {
        mNavigationController = viewDataBinding.bottomView.material()
                .addItem(R.drawable.main_home,
                        "首页",
                        ColorUtils.getColor(R.color.main_bottom_check_color))
                .addItem(R.drawable.main_community,
                        "社区",
                        ColorUtils.getColor(R.color.main_bottom_check_color))
                .addItem(R.drawable.main_notify,
                        "通知",
                        ColorUtils.getColor(R.color.main_bottom_check_color))
                .addItem(R.drawable.main_user,
                        "我的",
                        ColorUtils.getColor(R.color.main_bottom_check_color))
                .setDefaultColor(
                        ColorUtils.getColor(R.color.main_bottom_default_color))
                .enableAnimateLayoutChanges()
                .build();

        mNavigationController.setHasMessage(2, true);
        mNavigationController.setMessageNumber(3, 6);

        viewDataBinding.cvContentView.setOffscreenPageLimit(1);
        viewDataBinding.cvContentView.setAdapter(adapter);
        mNavigationController.setupWithViewPager(viewDataBinding.cvContentView);

    }

    private void initFragment() {

    }


    @Override
    protected IMvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void onRetryBtnClick() {

    }
}
