package leifu.mvpdemo.presenter;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import leifu.mvpdemo.base.RxBus;
import leifu.mvpdemo.base.RxPresenter;
import leifu.mvpdemo.base.RxUtil;
import leifu.mvpdemo.model.bean.BaseBean;
import leifu.mvpdemo.model.http.RetrofitHelper;
import leifu.mvpdemo.presenter.contract.TestDaggerContract;
import leifu.mvpdemo.utils.Logger;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/22 11:45
 * 描述:
 */

public class BusPresenter extends RxPresenter<TestDaggerContract.View> implements TestDaggerContract.Presenter {
    RetrofitHelper retrofitHelper;

    @Inject
    public BusPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getDailyList(HashMap<String, String> paramsMap) {
        Logger.e("baseBean" + paramsMap.toString());
        addSubscribe(RxBus.getDefault().toFlowable(BaseBean.class)
                .compose(RxUtil.<BaseBean>rxSchedulerHelper())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        Logger.e("baseBean" + baseBean.getState());
                        mView.showContent(baseBean);
                    }
                }));


    }
}
