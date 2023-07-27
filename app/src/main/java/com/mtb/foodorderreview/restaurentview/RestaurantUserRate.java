package com.mtb.foodorderreview.restaurentview;

public class RestaurantUserRate {
    private String name;
    private int starCount;
    private String userNote;

    public RestaurantUserRate(String username, int starCount, String userNote) {
        this.name = username;
        this.starCount = starCount;
        this.userNote = userNote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
}
