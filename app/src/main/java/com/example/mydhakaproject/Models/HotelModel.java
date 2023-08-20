package com.example.mydhakaproject.Models;

public class HotelModel {

    private int hotel_id;
    private String hotel_name;
    private String hotel_address;
    private String hotel_phone;
    private String hotel_email;
    private String hotel_website;
    private String hotel_image;


    public HotelModel(int hotel_id, String hotel_name, String hotel_address, String hotel_phone, String hotel_email, String hotel_website, String hotel_image) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_address = hotel_address;
        this.hotel_phone = hotel_phone;
        this.hotel_email = hotel_email;
        this.hotel_website = hotel_website;
        this.hotel_image = hotel_image;
    }

    public HotelModel() {
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_address() {
        return hotel_address;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_email() {
        return hotel_email;
    }

    public void setHotel_email(String hotel_email) {
        this.hotel_email = hotel_email;
    }

    public String getHotel_website() {
        return hotel_website;
    }

    public void setHotel_website(String hotel_website) {
        this.hotel_website = hotel_website;
    }

    public String getHotel_image() {
        return hotel_image;
    }

    public void setHotel_image(String hotel_image) {
        this.hotel_image = hotel_image;
    }
}
