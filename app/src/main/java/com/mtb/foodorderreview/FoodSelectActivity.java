package com.mtb.foodorderreview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.global.CartFood;
import com.mtb.foodorderreview.global.CartGlobal;
import com.mtb.foodorderreview.global.RestaurantFoodGlobal;
import com.mtb.foodorderreview.global.RestaurantGlobal;
import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.restaurentview.RestaurantFood;
import com.mtb.foodorderreview.utils.Utils;

public class FoodSelectActivity extends AppCompatActivity {
    TextView food_select_name,
            food_select_price,
            food_select_description,
            food_select_quantity_text;
    ImageView food_select_banner;
    LinearLayout food_select_back_btn,
            food_select_search_btn,
            food_select_decrease_btn,
            food_select_increase_btn,
            food_select_share_btn;
    Button food_select_add_to_cart_btn;
    RestaurantFood food;
    int countQuantity = 1;
    final String ADD_TO_CART_STR = "Thêm - ";
    CartGlobal cartGlobal;
    Restaurant restaurant;

    enum Mode {
        INC,
        DES
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_select);

        initialization();
        updateQuantityUi();
        onQuantityChangeBtns();

        Utils.UI.backBtn(this, food_select_back_btn);

        addToCartBtn();
    }

    private void initialization() {
        food_select_name = findViewById(R.id.food_select_name);
        food_select_price = findViewById(R.id.food_select_price);
        food_select_description = findViewById(R.id.food_select_description);
        food_select_quantity_text = findViewById(R.id.food_select_quantity_text);
        food_select_banner = findViewById(R.id.food_select_banner);
        food_select_back_btn = findViewById(R.id.food_select_back_btn);
        food_select_search_btn = findViewById(R.id.food_select_search_btn);
        food_select_decrease_btn = findViewById(R.id.food_select_decrease_btn);
        food_select_increase_btn = findViewById(R.id.food_select_increase_btn);
        food_select_share_btn = findViewById(R.id.food_select_share_btn);
        food_select_add_to_cart_btn = findViewById(R.id.food_select_add_to_cart_btn);

        restaurant = RestaurantGlobal.getInstance().getRestaurant();
        food = RestaurantFoodGlobal.getInstance().getFood();

        food_select_name.setText(food.getName());

        String money = Utils.currency(food.getPrice());
        food_select_price.setText(money);
        Utils.UI.setSrc(food.getImage(), food_select_banner);
        food_select_description.setText(food.getDescription());

        cartGlobal = CartGlobal.getInstance();
    }

    private void onQuantityChangeBtns() {
        onQuantityChangeBtn(food_select_decrease_btn, Mode.DES);
        onQuantityChangeBtn(food_select_increase_btn, Mode.INC);
    }

    private void onQuantityChangeBtn(LinearLayout btn, Mode mode) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode == Mode.INC)
                    countQuantity++;
                else if (countQuantity >= 2)
                    countQuantity--;
                if (countQuantity == 1) {
                    food_select_decrease_btn.setBackgroundResource(R.drawable.shape_border_box_disable);
                } else {
                    food_select_decrease_btn.setBackgroundResource(R.drawable.shape_border_box);
                }
                updateQuantityUi();
            }
        });
    }

    private void updateQuantityUi() {
        String price = Utils.currency(countQuantity * food.getPrice());
        String newBtnText = ADD_TO_CART_STR + price;
        food_select_quantity_text.setText(String.valueOf(countQuantity));
        food_select_add_to_cart_btn.setText(newBtnText);
    }

    private void addToCartBtn() {
        food_select_add_to_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartGlobal.getRestaurant() == null || cartGlobal.getRestaurant().getId() != restaurant.getId()) {
                    cartGlobal.reset();
                    cartGlobal.setRestaurant(restaurant);
                }
                cartGlobal.addFood(new CartFood(food, countQuantity));

                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }

}