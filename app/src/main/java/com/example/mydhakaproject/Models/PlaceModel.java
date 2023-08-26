package com.example.mydhakaproject.Models;

public class PlaceModel {
    private String name;
    private String image;
    private String about;
    private String google_map;

    public PlaceModel() {
    }

    public PlaceModel(String name, String image, String about, String google_map) {
        this.name = name;
        this.image = image;
        this.about = about;
        this.google_map = google_map;
    }

    // Getter and setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for image
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Getter and setter methods for about
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    // Getter and setter methods for google_map
    public String getGoogleMap() {
        return google_map;
    }

    public void setGoogleMap(String google_map) {
        this.google_map = google_map;
    }

    @Override
    public String toString() {
        return "PlaceModel{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", about='" + about + '\'' +
                ", google_map='" + google_map + '\'' +
                '}';
    }

    // Other methods if needed
}

