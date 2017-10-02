package com.andermaco.test.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by andermaco@gmail.com on 20/09/17.
 */

/**
 * Areas: Science (blue), Engineering (red) or Command (yellow)
 */
public class Area extends RealmObject {

    @PrimaryKey
    private int id;

    private String name;

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


}
