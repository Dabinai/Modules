package com.dabin.module_home;

import com.dabin.library_base.model.BaseModel;
import com.dabin.library_base.utils.GsonUtils;
import com.dabin.library_common.contract.BaseCustomViewModel;
import com.dabin.module_home.discover.bean.CategoryCardBean;
import com.dabin.module_home.discover.bean.SubjectCardBean;
import com.dabin.module_home.discover.bean.TextCardbean;
import com.dabin.module_home.discover.bean.TopBannerBean;
import com.dabin.module_home.discover.bean.viewmodel.TopBannerViewModel;
import com.dabin.module_home.nominate.bean.viewmodel.TitleViewModel;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class DiscoverModel<T> extends BaseModel<T> {
    public static final String DEFAULT_URL =
            "http://baobab.kaiyanapp.com/api/v7/index/tab/discovery?udid=fa53872206ed42e3857755c2756ab683fc22d64a&vc=591&vn=6.2.1&size=720X1280&deviceModel=Che1-CL20&first_channel=eyepetizer_zhihuiyun_market&last_channel=eyepetizer_zhihuiyun_market&system_version_code=19";
    private Disposable disposable;

    @Override
    protected void load() {
        disposable = EasyHttp.get(DEFAULT_URL)
                .cacheKey(getClass().getSimpleName())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        loadFail(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        parseJson(s);
                    }
                });
    }

    private void parseJson(String s) {
        List<BaseCustomViewModel> viewModels = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray itemList = jsonObject.optJSONArray("itemList");
            if (itemList != null) {
                for (int i = 0; i < itemList.length(); i++) {
                    JSONObject ccurrentObject = itemList.getJSONObject(i);
                    switch (ccurrentObject.getString("type")) {
                        case "horizontalScrollCard":
                            TopBannerBean topBannerBean = GsonUtils.fromLocalJson(ccurrentObject.toString(), TopBannerBean.class);
                            TopBannerViewModel topBannerViewModel = new TopBannerViewModel();
                            topBannerViewModel.bannerUrl = topBannerBean.getData().getItemList().get(0).getData().getImage();
                            viewModels.add(topBannerViewModel);
                            break;
                        case "specialSquareCardCollection":
                            CategoryCardBean categoryCardBean = GsonUtils.fromLocalJson(ccurrentObject.toString(),CategoryCardBean.class);
                            viewModels.add(categoryCardBean);
                            break;
                        case "columnCardList":
                            SubjectCardBean subjectCardBean = GsonUtils.fromLocalJson(ccurrentObject.toString(),SubjectCardBean.class);
                            viewModels.add(subjectCardBean);
                            break;
                        case "textCard":
                            TextCardbean textCardbean = GsonUtils.fromLocalJson(ccurrentObject.toString(),TextCardbean.class);
                            TitleViewModel titleViewModel = new TitleViewModel();
                            titleViewModel.title = textCardbean.getData().getText();
                            titleViewModel.actionTitle = textCardbean.getData().getRightText();
                            viewModels.add(titleViewModel);
                            break;
                        case "banner":

                            break;
                        case "videoSmallCard":
                            break;
                        case "briefCard":
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
