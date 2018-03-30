package leifu.mvpdemo.di.component;

import android.app.Activity;

import dagger.Component;
import leifu.mvpdemo.di.module.ActivityModule;
import leifu.mvpdemo.di.scope.ActivityScope;
import leifu.mvpdemo.ui.LoginActivity;
import leifu.mvpdemo.ui.TestDaggerActivity;
import leifu.mvpdemo.ui.UpPicActivity;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/21 16:08
 * 描述:
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(TestDaggerActivity activity);

    void inject(LoginActivity activity);

    void inject(UpPicActivity activity);
}

