package com.mtb.foodorderreview.global;

import com.mtb.foodorderreview.homeview.Restaurant;

import java.util.List;

public class RestaurantListGlobal {
    private List<Restaurant> list;
    private static final RestaurantListGlobal instance = new RestaurantListGlobal();

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public static RestaurantListGlobal getInstance() {
        return instance;
    }

    private RestaurantListGlobal() {
    }

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public List<Restaurant> getList() {
        return list;
    }

    public void setList(List<Restaurant> list) {
        this.list = list;
    }
}
