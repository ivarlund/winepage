package com.project.uniqo.models;

public class WineGrape {

    private int wineId;
    private int grapeId;

    private Wine wine;
    private Grape grape;

    public int getWineId() {
        return wineId;
    }

    public void setWineId(int wineId) {
        this.wineId = wineId;
    }

    public int getGrapeId() {
        return grapeId;
    }

    public void setGrapeId(int grapeId) {
        this.grapeId = grapeId;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public Grape getGrape() {
        return grape;
    }

    public void setGrape(Grape grape) {
        this.grape = grape;
    }
}
