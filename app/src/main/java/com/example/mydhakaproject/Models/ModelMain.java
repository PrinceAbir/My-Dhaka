package com.example.mydhakaproject.Models;

public class ModelMain {

    private int imageLink;
    private String name;

    public ModelMain(int imageLink, String name) {
        this.imageLink = imageLink;
        this.name = name;
    }

    public int getImageLink() {
        return imageLink;
    }

    public void setImageLink(int imageLink) {
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
