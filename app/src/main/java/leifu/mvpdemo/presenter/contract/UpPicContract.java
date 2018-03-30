package leifu.mvpdemo.presenter.contract;

import leifu.mvpdemo.base.BasePresenter;
import leifu.mvpdemo.base.BaseView;
import leifu.mvpdemo.model.bean.BaseBean;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/22 11:47
 * 描述:
 */

public interface UpPicContract {
    interface View extends BaseView {
        void showContent(BaseBean baseBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getUpPic(RequestBody meeting_id, MultipartBody.Part file);
    }
}
