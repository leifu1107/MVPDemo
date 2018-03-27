package leifu.mvpdemo.ui;

import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import leifu.mvpdemo.R;
import leifu.mvpdemo.base.BaseActivity;
import leifu.mvpdemo.model.bean.BaseBean;
import leifu.mvpdemo.presenter.TestDaggerPresenter;
import leifu.mvpdemo.presenter.contract.TestDaggerContract;
import leifu.mvpdemo.utils.JsonUtil;
import leifu.mvpdemo.utils.Logger;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/22 11:38
 * 描述:
 */

public class TestDaggerActivity extends BaseActivity<TestDaggerPresenter> implements TestDaggerContract.View {
    @BindView(R.id.text)
    TextView text;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("name", "1112312");
        paramsMap.put("phone", "11122312");
        mPresenter.getDailyList(paramsMap);
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void showContent(BaseBean baseBean) {
        Logger.e(JsonUtil.toJson(baseBean));
        showErrorMsg("状态--" + baseBean.getState() + baseBean.getMsg());

    }
}
