package com.example.app1_new.models;

import java.io.Serializable;

public class Place implements Serializable { // giao dien nay giup ta dong goi doi tuong mang di noi khac va co the lay ra cac phan tu theo index
    private String name;
    private String description;
    private Integer placeId;

    public Place() {
    }

    public Place(String name, String description, Integer placeId) {
        this.name = name;
        this.description = description;
        this.placeId = placeId;
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
    public Integer getPlaceId() {
        return this.placeId;
    }
    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
