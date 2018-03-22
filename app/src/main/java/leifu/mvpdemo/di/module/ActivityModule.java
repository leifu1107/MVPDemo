package leifu.mvpdemo.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import leifu.mvpdemo.di.scope.ActivityScope;

/**
 *创建人: 雷富
 * 创建时间: 2018/3/13 16:19
 * 描述:
 */

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
