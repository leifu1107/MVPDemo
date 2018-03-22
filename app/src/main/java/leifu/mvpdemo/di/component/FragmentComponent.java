package leifu.mvpdemo.di.component;

import android.app.Activity;

import dagger.Component;
import leifu.mvpdemo.di.module.FragmentModule;
import leifu.mvpdemo.di.scope.FragmentScope;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();


}
