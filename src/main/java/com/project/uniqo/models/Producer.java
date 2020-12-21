package com.project.uniqo.models;

import java.util.Collection;

public class Producer {

    private int id;
    private String name;
    private String description;

    private Collection<Wine> wines;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Wine> getWines() {
        return wines;
    }

    public void setWines(Collection<Wine> wines) {
        this.wines = wines;
    }
}
