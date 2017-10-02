package com.andermaco.test.ui.di.components;

import android.content.Context;

import com.andermaco.test.App;
import com.andermaco.test.model.realm.RealmService;
import com.andermaco.test.ui.di.modules.ApplicationModule;
import com.andermaco.test.ui.di.modules.RealmModule;
import com.andermaco.test.ui.di.scopes.PerApplication;

import dagger.Component;
import io.realm.Realm;

/**
 * Created by andermaco@gmail.com on 20/09/17.
 */

@PerApplication
@Component(modules = {
        ApplicationModule.class,
        RealmModule.class

})
public interface ApplicationComponent {

    void inject(App application);

    Context getContext();

    Realm provideRealm();

    RealmService provideRealmService();

}
