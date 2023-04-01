package com.example.utaeventtracker;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private String name;
    private String description;
    private String venue;
    private String imageUrl;
    private String date;

    public Event(int id, String name, String description, String venue, String imageUrl, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.venue = venue;
        this.imageUrl = imageUrl;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDate() {
        return date;
    }
}

