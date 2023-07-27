package com.mtb.foodorderreview.global;

import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.restaurentview.RestaurantCoupon;
import com.mtb.foodorderreview.utils.IChangeListener;

import java.util.ArrayList;
import java.util.List;

public class CartGlobal {
    private Restaurant restaurant;
    private List<CartFood> foodList = new ArrayList<>();
    private RestaurantCoupon coupon;
    private List<IChangeListener<CartGlobal>> changeListeners = new ArrayList<>();

    //    private List<RestaurantCoupon> couponList = new ArrayList<>();
    private static final CartGlobal instance = new CartGlobal();

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public static CartGlobal getInstance() {
        return instance;
    }


    private CartGlobal() {
    }

    public void reset() {
        this.restaurant = null;
        this.coupon = null;
        this.foodList = new ArrayList<>();

        this.runListener();
//        couponList = new ArrayList<>();
    }

    public void addListener(IChangeListener<CartGlobal> changeListener) {
        for (IChangeListener<CartGlobal> c : changeListeners) {
            if (c.getId() == changeListener.getId()) return;
        }

        this.changeListeners.add(changeListener);
    }

    private void runListener() {
        this.changeListeners.forEach(r -> {
            r.dataChange(instance);
        });
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public CartFood findCartFoodByFoodId(int id) {
        for (CartFood f : foodList)
            if (f.getFood().getId() == id) {
                return f;
            }

        return null;
    }

    public void addFood(CartFood food) {
        CartFood ff = this.findCartFoodByFoodId(food.getFood().getId());

        if (ff != null) {
            ff.setQuantity(ff.getQuantity() + food.getQuantity());
            return;
        }

        this.foodList.add(food);
        runListener();
    }

    public void removeFood(CartFood food) {
        this.foodList.remove(food);
        runListener();
    }

    public int calSubtotal() {
        int s = 0;

        for (CartFood c : foodList) {
            s = s + c.getQuantity() * c.getFood().getPrice();
        }

        return s;
    }

    public int calDiscount() {
        int s = 0;
        int subTotal = this.calSubtotal();

        if (coupon == null) return 0;
        double discountV = coupon.getDiscount();

        switch (coupon.getType()) {
            case FIXED:
                s = (int) Math.floor(discountV);
                break;

            case PERCENT:
                s = (int) Math.floor(subTotal * discountV);
                break;
        }

        return s;
    }

//    public RestaurantCoupon findCouponById(int id) {
//        for (RestaurantCoupon f : couponList)
//            if (f.getId() == id) {
//                return f;
//            }
//
//        return null;
//    }

//    public void addCoupon(RestaurantCoupon coupon) {
//        RestaurantCoupon ff = this.findCouponById(coupon.getId());
//
//        if (ff == null) this.couponList.add(coupon);
//    }


    public RestaurantCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(RestaurantCoupon coupon) {
        this.coupon = coupon;
        runListener();
    }

    public List<CartFood> getFoodList() {
        return foodList;
    }

}
