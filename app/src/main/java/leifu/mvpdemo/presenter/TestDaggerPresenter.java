package leifu.mvpdemo.presenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import leifu.mvpdemo.base.RxPresenter;
import leifu.mvpdemo.base.RxUtil;
import leifu.mvpdemo.model.RetrofitHelper;
import leifu.mvpdemo.model.bean.GankHttpResponse;
import leifu.mvpdemo.model.bean.GankItemBean;
import leifu.mvpdemo.presenter.contract.TestDaggerContract;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/22 11:45
 * 描述:
 */

public class TestDaggerPresenter extends RxPresenter<TestDaggerContract.View> implements TestDaggerContract.Presenter {
    RetrofitHelper retrofitHelper;

    @Inject
    public TestDaggerPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getDailyList() {
        addSubscribe(retrofitHelper.getWXHot("Android", 20, 1)
                .compose(RxUtil.<GankHttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankItemBean>>handleResult())
                .subscribe(new Consumer<List<GankItemBean>>() {
                    @Override
                    public void accept(List<GankItemBean> gankItemBeen) throws Exception {

                    }
                })
        );


    }
}
