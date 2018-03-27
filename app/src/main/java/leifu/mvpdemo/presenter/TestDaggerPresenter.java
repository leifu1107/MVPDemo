package leifu.mvpdemo.presenter;

import java.util.HashMap;

import javax.inject.Inject;

import leifu.mvpdemo.base.RxPresenter;
import leifu.mvpdemo.base.RxUtil;
import leifu.mvpdemo.model.bean.BaseBean;
import leifu.mvpdemo.model.exception.CommonSubscriber;
import leifu.mvpdemo.model.http.PostParamsHelper;
import leifu.mvpdemo.model.http.RetrofitHelper;
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
    public void getDailyList(HashMap<String, String> paramsMap) {
        addSubscribe(retrofitHelper.getBaseBean(PostParamsHelper.putParams(paramsMap))
                .compose(RxUtil.<BaseBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<BaseBean>(mView) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        mView.showContent(baseBean);
                    }
                })
//                .subscribe(new Consumer<BaseBean>() {
//                    @Override
//                    public void accept(BaseBean baseBean) throws Exception {
//                        mView.showContent(baseBean);
//                    }
//                })

        );


    }
}
