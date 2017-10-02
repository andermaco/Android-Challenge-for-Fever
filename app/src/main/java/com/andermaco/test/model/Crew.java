package com.andermaco.test.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by andermaco@gmail.com on 20/09/17.
 */

public class Crew extends RealmObject {

    @PrimaryKey
    private int id;

    private String name;

    private Area area;

    private Race race;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
