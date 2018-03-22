package leifu.mvpdemo.presenter;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import leifu.mvpdemo.base.RxPresenter;
import leifu.mvpdemo.base.RxUtil;
import leifu.mvpdemo.model.RetrofitHelper;
import leifu.mvpdemo.model.bean.DailyListBean;
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
        addSubscribe(retrofitHelper.fetchDailyListInfo()
        .compose(RxUtil.<DailyListBean>rxSchedulerHelper())
                .subscribe(new Consumer<DailyListBean>() {
                    @Override
                    public void accept(DailyListBean dailyListBean) throws Exception {
                        mView.showContent(dailyListBean);
                    }
                })
        );

    }
}
