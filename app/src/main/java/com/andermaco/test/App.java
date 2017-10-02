package com.andermaco.test;

import android.app.Application;
import android.content.Context;

import com.andermaco.test.model.realm.InitialDataRealmTransaction;
import com.andermaco.test.ui.di.components.ApplicationComponent;
import com.andermaco.test.ui.di.components.DaggerApplicationComponent;
import com.andermaco.test.ui.di.modules.ApplicationModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by andermaco@gmail.com on 20/09/17.
 */

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize application
        initApp();
    }

    /**
     * Initialize application
     */
    private void initApp() {
        setupInjector();
        initRealmConfiguration();
    }

    /**
     * Setup dagger 2 injector
     */
    private void setupInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    /**
     * Init realm
     */
    private void initRealmConfiguration() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
            .initialData(new InitialDataRealmTransaction())
            .build();
        Realm.setDefaultConfiguration(config);
    }

    /**
     * Get an Application instance
     * @param context Current context
     * @return Application
     */
    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }

    /**
     * @return Dagger2's application component, so dependencies can be handled.
     */
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
