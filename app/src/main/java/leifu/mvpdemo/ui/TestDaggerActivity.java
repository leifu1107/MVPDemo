package leifu.mvpdemo.ui;

import leifu.mvpdemo.R;
import leifu.mvpdemo.base.BaseActivity;
import leifu.mvpdemo.model.bean.DailyListBean;
import leifu.mvpdemo.presenter.TestDaggerPresenter;
import leifu.mvpdemo.presenter.contract.TestDaggerContract;
import leifu.mvpdemo.utils.Logger;
import leifu.toastlibrary.CustomToast;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/22 11:38
 * 描述:
 */

public class TestDaggerActivity extends BaseActivity<TestDaggerPresenter> implements TestDaggerContract.View {
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {

//        mPresenter.getDailyList();
    }

    @Override
    protected void initInject() {

//        getActivityComponent().inject(this);
    }

    @Override
    public void showContent(DailyListBean dailyListBean) {
        CustomToast.success("111" + dailyListBean.getDate());
        Logger.e("aaa" + dailyListBean.getDate());
    }
}
