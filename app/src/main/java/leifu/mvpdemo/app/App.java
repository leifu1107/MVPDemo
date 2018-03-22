package leifu.mvpdemo.app;

import android.app.Activity;
import android.app.Application;

import java.util.HashSet;
import java.util.Set;

import leifu.mvpdemo.di.component.AppComponent;
import leifu.mvpdemo.di.component.DaggerAppComponent;
import leifu.mvpdemo.di.module.AppModule;
import leifu.toastlibrary.CustomToast;

/**
 * 创建人: 雷富
 * 创建时间: 2018/3/19 15:33
 * 描述:
 */

public class App extends Application {
    private static App instance;
    private static AppComponent appComponent;
    private Set<Activity> allActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        LeakCanary.install(this);
        CustomToast.init(this);

    }

    public static synchronized App getInstance() {
        return instance;
    }


    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }
}
