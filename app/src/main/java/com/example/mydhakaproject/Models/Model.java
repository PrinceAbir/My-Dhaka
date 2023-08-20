package com.example.mydhakaproject.Models;

public class Model {

    private String name;
    private String category;
    private String address;
    private String phone1;
    private String phone2;

    private int callButton;

    public Model(String name, String category, String address, String phone1, String phone2, int call) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.callButton = call;
    }

    public Model() {

    }

    public Model(String name, String address, String phone1, String phone2,int call) {
        this.name = name;
        this.address = address;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.callButton = call;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public int getCallButton() {
        return callButton;
    }

    public void setCallButton(int callButton) {
        this.callButton = callButton;
    }
}
