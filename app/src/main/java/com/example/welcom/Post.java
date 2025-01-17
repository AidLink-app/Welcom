package com.example.welcom;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private String title;
    private String description;
    private String date;
    private String location;
    private String organization;
    private String category;
    private String imageUrl;
    private String whatsapp_link;
    private List<String> registeredUsers = new ArrayList<>();


    // Default Constructor (Required for Firestore)
    public Post() {
    }

    // Constructor with all fields
    public Post(String title, String description, String date, String location,
                String organization, String category, String imageUrl, String whatsapp_link) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.organization = organization;
        this.category = category;
        this.imageUrl = imageUrl;
        this.whatsapp_link = whatsapp_link;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getOrganization() {
        return organization;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public List<String> getRegisteredUsers() {
        return registeredUsers;
    }

    public String getWhatsapp_link() { return whatsapp_link;}

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setWhatsapp_link(String whatsapp_link) { this.whatsapp_link = whatsapp_link;}
    public void setRegisteredUsers(List<String> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }
}