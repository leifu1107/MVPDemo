package leifu.mvpdemo.di.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import leifu.mvpdemo.app.App;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/13 16:19
 * 描述:
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }


}
