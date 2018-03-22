package leifu.mvpdemo.di.component;

import javax.inject.Singleton;

import dagger.Component;
import leifu.mvpdemo.app.App;
import leifu.mvpdemo.di.module.AppModule;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/21 16:06
 * 描述:
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    App getContext();  // 提供App的Context
}
