package com.andermaco.test.ui.crew;

import com.andermaco.test.model.Crew;

import io.realm.RealmResults;

/**
 * Created by andermaco@gmail.com on 20/09/17.
 */

public interface CrewView {
    public void showCrew(RealmResults<Crew> allCrew);
}
