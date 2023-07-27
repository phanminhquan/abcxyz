package com.mtb.foodorderreview.something;

import com.mtb.foodorderreview.global.CartFood;
import com.mtb.foodorderreview.homeview.Restaurant;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Restaurant restaurant;
    private Date createdAt;
    private STATE state;
    private List<CartFood> cartFood;
    private int finalPrice;
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public Order(Restaurant restaurant, List<CartFood> cartFood) {
        this.restaurant = restaurant;
        this.createdAt = new Date();
        this.state = STATE.PENDING;
        this.cartFood = cartFood;
    }

    public Order(int id, Restaurant restaurant, Date createdAt, List<CartFood> cartFood, STATE state, int finalPrice) {
        this.id = id;
        this.restaurant = restaurant;
        this.createdAt = createdAt;
        this.state = state;
        this.cartFood = cartFood;
        this.finalPrice = finalPrice;
    }
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public STATE getState() {
        return state;
    }

    public String getStateStr() {
        switch (state) {
            case PENDING:
                return "Đang đặt món";
            case PREPARING:
                return "Đang chuẩn bị";
            case DELIVERING:
                return "Đang giao";
            case DELIVERED:
                return "Đã giao";
        }
        return "";
    }

    public List<CartFood> getCartFood() {
        return cartFood;
    }

    public void setCartFood(List<CartFood> cartFood) {
        this.cartFood = cartFood;
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public enum STATE {
        PENDING,
        PREPARING,
        DELIVERING,
        DELIVERED;

        public int getValue() {
            switch (this) {
                case PENDING:
                    return 1;
                case PREPARING:
                    return 2;
                case DELIVERING:
                    return 3;
                case DELIVERED:
                    return 4;

                default:
                    return -1;
            }
        }

        public static STATE getState(int id) {
            switch (id) {
                case 1:
                    return PENDING;
                case 2:
                    return PREPARING;
                case 3:
                    return DELIVERING;
                case 4:
                    return DELIVERED;
            }

            return PENDING;
        }
    }
}