package leifu.mvpdemo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import leifu.mvpdemo.R;
import leifu.mvpdemo.app.App;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by codeest on 16/8/11.
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends SupportActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;

    public TextView mCenterTitle;
    public LinearLayout mTitleLayout;
    public TextView mRightText;
    public ImageView mBtnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        App.getInstance().addActivity(this);
        initEventAndData();
    }


    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }
    /**
     * 设置布局标题头
     *
     * @param centerTitle 中间标题
     * @param rightText   布局最右边文字
     * @param bgColor     整个头布局背景颜色
     */
    public void setTitleText(String centerTitle, String rightText, int bgColor) {
        mCenterTitle = (TextView) findViewById(R.id.centerTitle);
        mTitleLayout = (LinearLayout) findViewById(R.id.titleLayout);
        mRightText = (TextView) findViewById(R.id.rightText);
        mBtnBack = (ImageView) findViewById(R.id.btnback);
        mCenterTitle.setText(centerTitle);
        mRightText.setText(rightText);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleLayout.setBackgroundResource(bgColor);
    }
    public void mStartActivity(Class<?> intentActivity) {
        Intent intent = new Intent(mContext, intentActivity);
        super.startActivity(intent);
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();
}
