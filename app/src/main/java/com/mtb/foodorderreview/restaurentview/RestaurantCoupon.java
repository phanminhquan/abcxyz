package com.mtb.foodorderreview.restaurentview;

public class RestaurantCoupon {
    private int id;
    private String title;
    private String code;
    private double discount;

    private DiscountType type;

    public RestaurantCoupon(int id, String title, String code, double discount, DiscountType type) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.discount = discount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getDiscount() {
        return discount;
    }

    public String getCode() {
        return code;
    }

    public DiscountType getType() {
        return type;
    }

    public enum DiscountType {
        PERCENT,
        FIXED;

        public int getValue() {
            switch (this) {
                case PERCENT:
                    return 1;
                case FIXED:
                    return 2;
                default:
                    return 1;
            }
        }
    }
}
