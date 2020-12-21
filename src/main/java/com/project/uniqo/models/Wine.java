package com.project.uniqo.models;

import java.util.Collection;

public class Wine {

    private int id;
    private String name;
    private String type;
    private String country;
    private String region;
    private int year;
    private String description;
    private int producerId;

    private Producer producer;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Collection<WineGrape> getWineGrapes() {
        return wineGrapes;
    }

    public void setWineGrapes(Collection<WineGrape> wineGrapes) {
        this.wineGrapes = wineGrapes;
    }

}