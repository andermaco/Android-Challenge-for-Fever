package com.andermaco.test.model.realm;


import com.andermaco.test.model.Area;
import com.andermaco.test.model.Crew;
import com.andermaco.test.model.Race;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmService {

    private final Realm mRealm;

    public RealmService(final Realm realm) {
        mRealm = realm;
    }

    public void closeRealm() {
        mRealm.close();
    }

    public RealmResults<Crew> getAllCrewByName() {
        return mRealm.where(Crew.class).findAll().sort("name");
    }

    public RealmResults<Crew> getAllCrewByPosition() {
        return mRealm.where(Crew.class).findAll().sort("area");
    }

    public RealmResults<Area> getAllAreas() {
        return mRealm.where(Area.class).findAll();
    }

    public RealmResults<Race> getAllRaces() {
    return mRealm.where(Race.class).findAll();
}

    public Crew getCrew(final int crewId) {
        return mRealm.where(Crew.class).equalTo("id", crewId).findFirst();
    }

    public Area getArea(final int areaId) {
            return mRealm.where(Area.class).equalTo("id", areaId).findFirst();
    }

    public Race getRace(final int raceId) {
            return mRealm.where(Race.class).equalTo("id", raceId).findFirst();
    }

    public void addCrew(final String name, final String area, final String race) {
        Crew crew = mRealm.createObject(Crew.class, mRealm.where(Crew.class).findAll().size());
        crew.setName(name);
        crew.setArea(createOrGetArea(area, mRealm));
        crew.setRace(createOrGetRace(race, mRealm));
        mRealm.insertOrUpdate(crew);
    }

    public void addRace(final String name) {
        Race race = mRealm.createObject(Race.class, mRealm.where(Race.class).findAll().size());
        race.setName(name);
        mRealm.insertOrUpdate(race);
    }

    public void addArea(final String name) {
        Area area = mRealm.createObject(Area.class, mRealm.where(Area.class).findAll().size());
        area.setName(name);
        mRealm.insertOrUpdate(area);
    }

    private Area createOrGetArea(final String name, final Realm realm) {
            Area area = realm.where(Area.class).equalTo("name", name).findFirst();
            if (area == null) {
                area = addArea(name, realm);
            }
            return area;
    }

    private Race createOrGetRace(final String name, final Realm realm) {
            Race race =  realm.where(Race.class).equalTo("name", name).findFirst();
            if (race == null) {
                race = addRace(name, realm);
            }
            return race;
    }

    private Area addArea(final String name, final Realm realm) {
            Area area = realm.createObject(Area.class);
            area.setId(realm.where(Area.class).findAll().size());
            area.setName(name);
            return area;
    }

    private Race addRace(final String name, final Realm realm) {
            Race race = realm.createObject(Race.class);
            race.setId(realm.where(Race.class).findAll().size());
            race.setName(name);
            return race;
    }

}
