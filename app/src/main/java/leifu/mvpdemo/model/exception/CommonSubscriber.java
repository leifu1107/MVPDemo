package leifu.mvpdemo.model.exception;

import android.text.TextUtils;

import io.reactivex.subscribers.ResourceSubscriber;
import leifu.mvpdemo.base.BaseView;
import leifu.mvpdemo.dialog.ProgressDialogUtil;
import leifu.mvpdemo.utils.Logger;
import leifu.mvpdemo.utils.SystemUtils;
import retrofit2.HttpException;

/**
 * Created by codeest on 2017/2/23.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private String mErrorMsg;

    protected CommonSubscriber(BaseView view) {
        this.mView = view;
    }

    protected CommonSubscriber(BaseView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    //自己加的,防止出现问题,测试
    @Override
    protected void onStart() {
        super.onStart();
        if (!SystemUtils.isNetworkConnected()) {
            mView.showErrorMsg("检查网络设置");
            if (!isDisposed()) {
                dispose();
            }
            return;
        }
        mView.stateLoading();
    }

    @Override
    public void onComplete() {
        ProgressDialogUtil.stopWaitDialog();
    }

    @Override
    public void onError(Throwable e) {
        ProgressDialogUtil.stopWaitDialog();
        Logger.e("OkHttp:  " + e.toString());
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof ApiException) {
            mView.showErrorMsg(e.getMessage());
        } else if (e instanceof HttpException) {
            mView.showErrorMsg("数据加载失败ヽ(≧Д≦)ノ");
        } else {
            mView.showErrorMsg("未知错误ヽ(≧Д≦)ノ");
        }
    }
}
