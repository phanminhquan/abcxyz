package com.mtb.foodorderreview.restaurentview;

public class RestaurantFood {
    private int id;
    private String name;
    private String description;
    private String image;
    private int price;

    public RestaurantFood(int id, String name, String description, String image, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
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

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }
}
