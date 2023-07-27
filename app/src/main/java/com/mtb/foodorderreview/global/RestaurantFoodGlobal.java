package com.mtb.foodorderreview.global;

import com.mtb.foodorderreview.restaurentview.RestaurantFood;

public class RestaurantFoodGlobal {
    private RestaurantFood food;
    private static RestaurantFoodGlobal instance = new RestaurantFoodGlobal();

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    private RestaurantFoodGlobal() {
    }

    public static RestaurantFoodGlobal getInstance() {
        return instance;
    }

    public RestaurantFood getFood() {
        return food;
    }

    public void setFood(RestaurantFood food) {
        this.food = food;
    }
}
