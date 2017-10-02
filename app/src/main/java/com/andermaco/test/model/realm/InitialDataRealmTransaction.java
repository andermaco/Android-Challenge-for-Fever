package com.andermaco.test.model.realm;

import com.andermaco.test.Utils.Utils;
import com.andermaco.test.model.Crew;
import com.andermaco.test.model.Race;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by andermaco@gmail.com on 20/09/17.
 */

/**
 * Generates data for storing in Realm
 */
public class InitialDataRealmTransaction implements Realm.Transaction {

    public enum RACE {
        HUMAN, BETAZOID, VULCAN;
        public static RACE getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    public enum COLOR {
        BLUE, RED, YELLOW, OTHER;
    }

    public enum AREAS {
        SCIENCE, ENGINEERING, COMMAND;
        public static AREAS getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }

        public static COLOR getColor(String areas) {
            if (areas.equalsIgnoreCase(SCIENCE.name())) {
                return COLOR.BLUE;
            } else if (areas.equalsIgnoreCase(ENGINEERING.name())) {
                return COLOR.RED;
            } else if (areas.equalsIgnoreCase(COMMAND.name())) {
                return COLOR.YELLOW;
            } else {
                return COLOR.OTHER;
            }
        }
    }

    RealmService service;
    @Override
    public void execute(Realm realm) {
        service = new RealmService(realm);

        // Init Races
        service.addRace(RACE.HUMAN.name());
        service.addRace(RACE.VULCAN.name());
        service.addRace(RACE.BETAZOID.name());
        RealmResults<Race> allRaces = service.getAllRaces();


        // Init Areas
        service.addArea(AREAS.SCIENCE.name());
        service.addArea(AREAS.COMMAND.name());
        service.addArea(AREAS.ENGINEERING.name());

        for (int i=0; i<430; i++) {
            // Init Crew
            service.addCrew(Utils.randomString(6), AREAS.getRandom().name(),
                    RACE.getRandom().name());
        }

        RealmResults<Crew> allCrew = service.getAllCrewByName();
        System.out.println("-----> allCrew: " + allCrew.size());
    }
}
