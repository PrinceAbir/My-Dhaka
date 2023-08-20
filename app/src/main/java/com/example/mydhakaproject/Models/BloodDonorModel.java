package com.example.mydhakaproject.Models;

public class BloodDonorModel {

    String name;
    String address;
    String blood_group;
    String phone1;
    String phone2;

    int callButton;

    public BloodDonorModel(String name, String address, String blood_group, String phone1, String phone2,int callButton) {
        this.name = name;
        this.address = address;
        this.blood_group = blood_group;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.callButton = callButton;
    }

    public BloodDonorModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
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
