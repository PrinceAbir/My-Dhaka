package com.example.mydhakaproject.Models;

public class RestaurantModel {
    private String name;
    private String description;
    private String focus;
    private String address;
    private String phone;
    private String website;
    private String image_url;

    public RestaurantModel() {
    }

    public RestaurantModel(String name, String description, String focus, String address, String phone, String website, String image_url) {
        this.name = name;
        this.description = description;
        this.focus = focus;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.image_url = image_url;
    }

    // Getters and Setters for all variables
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

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
