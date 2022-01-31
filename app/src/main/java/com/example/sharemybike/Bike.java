package com.example.sharemybike;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Bike {
    public static List<Bike> ITEMS = new ArrayList<>();

    private String image;
    private String owner;
    private String description;
    private String city;
    private Double longitude;
    private Double latitude;
    private String location;
    private String country;


    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Bike(String image, String owner, String description, String city, Double longitude, Double latitude, String location, Bitmap photo, String email) {
        this.image = image;
        this.owner = owner;
        this.description = description;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.location = location;
        this.photo = photo;
        this.email = email;
    }

    public Bike(){

    }

    public Bike(String country, String city, String description,String email, String image, Double latitude, String location, Double longitude, String owner) {
        this.country=country;
        this.owner = owner;
        this.description = description;
        this.city = city;
        this.location = location;
        this.email=email;
        this.image=image;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Bike(String city, String description, Double latitude, String location, Double longitude) {
        this.description = description;
        this.city = city;
        this.location = location;
        this.latitude=latitude;
        this.longitude=longitude;
        this.photo= photo;
    }

    public Bike(Bitmap photo, String owner, String description, String city,  String location, String email) {
        this.photo = photo;
        this.owner = owner;
        this.description = description;
        this.city = city;
        this.location = location;
        this.email=email;
    }

    private Bitmap photo;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;





}

