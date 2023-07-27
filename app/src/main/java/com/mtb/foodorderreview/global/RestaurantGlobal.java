package com.mtb.foodorderreview.global;

import com.mtb.foodorderreview.homeview.Restaurant;

public class RestaurantGlobal {
    private Restaurant restaurant;
    private static final RestaurantGlobal restaurantGlobal = new RestaurantGlobal();

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public static RestaurantGlobal getInstance() {
        return restaurantGlobal;
    }

    private RestaurantGlobal() {
    }

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
