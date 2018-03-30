package leifu.mvpdemo.presenter;

import javax.inject.Inject;

import leifu.mvpdemo.base.RxPresenter;
import leifu.mvpdemo.base.RxUtil;
import leifu.mvpdemo.model.bean.BaseBean;
import leifu.mvpdemo.model.exception.CommonSubscriber;
import leifu.mvpdemo.model.http.RetrofitHelper;
import leifu.mvpdemo.presenter.contract.UpPicContract;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/22 11:45
 * 描述:
 */

public class UpPicPresenter extends RxPresenter<UpPicContract.View> implements UpPicContract.Presenter {
    RetrofitHelper retrofitHelper;

    @Inject
    public UpPicPresenter(RetrofitHelper retrofitHelper) {
        this.retrofitHelper = retrofitHelper;
    }

    @Override
    public void getUpPic(RequestBody meeting_id, MultipartBody.Part file) {
        addSubscribe(retrofitHelper.getUpLoad(meeting_id, file)
                .compose(RxUtil.<BaseBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<BaseBean>(mView) {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        mView.showContent(baseBean);
                    }
                })
        );
    }


}
