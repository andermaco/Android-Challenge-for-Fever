package com.andermaco.test.ui.crew;

import com.andermaco.test.model.realm.RealmService;
import com.andermaco.test.ui.base.BasePresenter;

/**
 * Created by andermaco@gmail.com on 20/09/17.
 */

public class CrewPresenter implements BasePresenter {

    private final RealmService mRealmService;
    private CrewView mView;

    private boolean crewShown = false;

    public CrewPresenter(CrewView crewView, RealmService mRealmService) {
        this.mView = crewView;
        this.mRealmService = mRealmService;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }

    public void closeRealm() {
        mRealmService.closeRealm();
    }

    public void showCrewByName() {
        if (!crewShown) {
            mView.showCrew(mRealmService.getAllCrewByName());
            crewShown = true;
        }
    }

    public void showCrewByPosition() {
        mView.showCrew(mRealmService.getAllCrewByPosition());
    }
}
