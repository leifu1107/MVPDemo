package leifu.mvpdemo.presenter.contract;

import java.util.HashMap;

import leifu.mvpdemo.base.BasePresenter;
import leifu.mvpdemo.base.BaseView;
import leifu.mvpdemo.model.bean.BaseBean;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/22 11:47
 * 描述:
 */

public interface TestDaggerContract {
    interface View extends BaseView {
        void showContent(BaseBean baseBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getDailyList(HashMap<String, String> paramsMap);
    }
}
