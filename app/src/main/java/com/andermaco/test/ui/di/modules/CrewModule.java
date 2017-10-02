package com.andermaco.test.ui.di.modules;

import com.andermaco.test.model.realm.RealmService;
import com.andermaco.test.ui.crew.CrewActivity;
import com.andermaco.test.ui.crew.CrewPresenter;
import com.andermaco.test.ui.crew.CrewView;
import com.andermaco.test.ui.di.scopes.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andermaco@gmail.com on 20/09/17.
 */

@Module
public class CrewModule {
    private final CrewActivity mActivity;

    public CrewModule(CrewActivity mActivity) {
        this.mActivity = mActivity;
    }

    @PerActivity
    @Provides
    public CrewView providesCrewActivity(){
        return this.mActivity;
    }

    @PerActivity
    @Provides
    public CrewPresenter providesCrewPresenter(RealmService realmService) {
        return new CrewPresenter(this.mActivity, realmService);
    }
}
