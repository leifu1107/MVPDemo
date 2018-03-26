package leifu.mvpdemo.presenter.contract;

import leifu.mvpdemo.base.BasePresenter;
import leifu.mvpdemo.base.BaseView;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/22 11:47
 * 描述:
 */

public interface TestDaggerContract {
    interface View extends BaseView {
//        void showContent(DailyListBean dailyListBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getDailyList();
    }
}
