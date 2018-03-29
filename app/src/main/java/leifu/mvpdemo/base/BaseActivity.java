package leifu.mvpdemo.base;

import javax.inject.Inject;

import leifu.mvpdemo.app.App;
import leifu.mvpdemo.di.component.ActivityComponent;
import leifu.mvpdemo.di.component.DaggerActivityComponent;
import leifu.mvpdemo.di.module.ActivityModule;
import leifu.mvpdemo.dialog.ProgressDialogUtil;
import leifu.mvpdemo.ui.LoginActivity;
import leifu.toastlibrary.CustomToast;

/**
 * Created by codeest on 2016/8/2.
 * MVP activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent() {

        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
        CustomToast.error(msg);
    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateLoading() {
        ProgressDialogUtil.showWaitDialog(mContext, "加载中");
    }

    @Override
    public void goLogin() {
        mStartActivity(LoginActivity.class);
    }

    protected abstract void initInject();
}