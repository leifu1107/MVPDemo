package leifu.mvpdemo.ui;

import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import leifu.mvpdemo.R;
import leifu.mvpdemo.base.BaseActivity;
import leifu.mvpdemo.model.bean.BaseBean;
import leifu.mvpdemo.presenter.BusPresenter;
import leifu.mvpdemo.presenter.contract.TestDaggerContract;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/28 13:44
 * 描述:
 */

public class LoginActivity extends BaseActivity<BusPresenter> implements TestDaggerContract.View {
    @BindView(R.id.text)
    TextView text;

    @Override
    protected int getLayout() {
        return R.layout.activity_bus;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getDailyList(new HashMap<String, String>());
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showContent(BaseBean baseBean) {
        text.setText(baseBean.getMsg() + baseBean.getState() + "rxbus");
    }
}
