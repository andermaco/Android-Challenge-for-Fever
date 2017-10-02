package com.andermaco.test.ui.di.modules;

import com.andermaco.test.model.realm.RealmService;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by andermaco@gmail.com on 20/09/17.
 */

@Module
public class RealmModule {

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    RealmService provideRealmService(final Realm realm) {
        return new RealmService(realm);
    }
}
