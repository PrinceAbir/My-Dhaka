package com.example.mydhakaproject.Models;

public class RoomModel {

    int Hotel_ID;
    String HotelName;
    String Hotel_Image;
    String RoomNumber;
    String RoomType;
    String BedCount;
    String Price;
    String Status;

    public RoomModel(String hotelName,int hotel_id, String hotel_image, String roomNumber, String roomType, String bedCount, String price, String status) {
        HotelName = hotelName;
        Hotel_ID = hotel_id;
        Hotel_Image = hotel_image;
        RoomNumber = roomNumber;
        RoomType = roomType;
        BedCount = bedCount;
        Price = price;
        Status = status;
    }

    public RoomModel() {
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public int getHotel_ID() {
        return Hotel_ID;
    }

    public void setHotel_ID(int hotel_ID) {
        Hotel_ID = hotel_ID;
    }

    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public String getRoomType() {
        return RoomType;
    }

    public String getHotel_Image() {
        return Hotel_Image;
    }

    public void setHotel_Image(String hotel_Image) {
        Hotel_Image = hotel_Image;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
    }

    public String getBedCount() {
        return BedCount;
    }

    public void setBedCount(String bedCount) {
        BedCount = bedCount;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
