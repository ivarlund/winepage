package com.project.uniqo.models;

import java.util.Collection;

public class Grape {

    private int id;
    private String name;

    private Collection<WineGrape> wineGrapes;

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

    public Collection<WineGrape> getWineGrapes() {
        return wineGrapes;
    }

    public void setWineGrapes(Collection<WineGrape> wineGrapes) {
        this.wineGrapes = wineGrapes;
    }
}
