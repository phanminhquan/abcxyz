package com.mtb.foodorderreview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.api.DonHangService;
import com.mtb.foodorderreview.checkout.CartFoodListViewAdapter;
import com.mtb.foodorderreview.components.ExpandableHeightListView;
import com.mtb.foodorderreview.global.CartGlobal;
import com.mtb.foodorderreview.global.OrderGlobal;
import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.model.DonHang;
import com.mtb.foodorderreview.something.Order;
import com.mtb.foodorderreview.utils.ICallback;
import com.mtb.foodorderreview.utils.Utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartCheckoutActivity extends AppCompatActivity {
    ExpandableHeightListView cart_checkout_listview;
    Button cart_checkout_submit_btn;
    TextView cart_checkout_subtotal_text,
            cart_checkout_shipping_text,
            cart_checkout_discount_text,
            cart_checkout_total_text;
    LinearLayout cart_checkout_back_btn;
    CartGlobal cartGlobal;

    final int SHIPPING_FEE = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_checkout);

        initialization();

        Utils.UI.backBtn(this, cart_checkout_back_btn);

        listView();

        updateUIPrice();

        orderSubmit();
    }

    private void initialization() {
        cart_checkout_listview = findViewById(R.id.cart_checkout_listview);
        cart_checkout_submit_btn = findViewById(R.id.cart_checkout_submit_btn);
        cart_checkout_subtotal_text = findViewById(R.id.cart_checkout_subtotal_text);
        cart_checkout_shipping_text = findViewById(R.id.cart_checkout_shipping_text);
        cart_checkout_discount_text = findViewById(R.id.cart_checkout_discount_text);
        cart_checkout_total_text = findViewById(R.id.cart_checkout_total_text);
        cart_checkout_back_btn = findViewById(R.id.cart_checkout_back_btn);

        cartGlobal = CartGlobal.getInstance();
    }

    public static String getStartDate() {
        DateTimeFormatter oldPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");
        DateTimeFormatter newPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime datetime = LocalDateTime.parse(new Date().toInstant().toString(), oldPattern);
        return datetime.format(newPattern);
    }

    private ICallback callback;

    private void orderSubmit() {
        cart_checkout_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order order = new Order(cartGlobal.getRestaurant(), cartGlobal.getFoodList());

                final ZoneId zone = ZoneId.of("Asia/Bangkok");
                LocalDateTime ldt = LocalDateTime.now();
                OffsetDateTime odt = ldt.atZone(zone).toOffsetDateTime();
                String total = cart_checkout_total_text.getText().toString();
                int index = total.indexOf('đ');
                total = total.substring(0, index);
                total = total.replaceFirst(",", "");
                DonHang donHang = new DonHang(null, 3, odt.toString(), "Đang giao", UserGlobal.getInstance().getId(), Double.parseDouble(total));

                final int[] id = new int[1];
                DonHangService.apiService.postDH(donHang).enqueue(new Callback<DonHang>() {
                    @Override
                    public void onResponse(Call<DonHang> call, Response<DonHang> response) {
                        DonHang d = response.body();
                        id[0] = d.getId();
                        callback.callback();

                        OrderGlobal.getInstance().setOrder(order);
                        order.setId(id[0]);
                        order.setState(Order.STATE.getState(d.getTrangThai()));
                    }

                    @Override
                    public void onFailure(Call<DonHang> call, Throwable t) {

                    }
                });

                callback = new ICallback() {
                    @Override
                    public void callback() {
                        cartGlobal.reset();
                        Intent intent = new Intent();


                        //intent.putExtra("idDonhang", id[0]);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                };


//                Intent intent = new Intent(CartCheckoutActivity.this, DeliveryActivity.class);
//                startActivity(intent);

            }
        });
    }

    private void listView() {
        CartFoodListViewAdapter adapter = new CartFoodListViewAdapter(this, cartGlobal.getFoodList());

        cart_checkout_listview.setAdapter(adapter);
        cart_checkout_listview.setExpanded(true);
    }

    private void updateUIPrice() {
        int subtotal = cartGlobal.calSubtotal();
        int shippingFee = subtotal == 0 ? 0 : SHIPPING_FEE;
        int discount = -cartGlobal.calDiscount();
        int total = subtotal + shippingFee + discount;

        if (total == 0) {
            cart_checkout_submit_btn.setEnabled(false);
            Utils.UI.setBackgroundTint(this, cart_checkout_submit_btn, R.color.grey_3);
        }

        cart_checkout_subtotal_text.setText(Utils.currency(subtotal));
        cart_checkout_shipping_text.setText(Utils.currency(shippingFee));
        cart_checkout_discount_text.setText(Utils.currency(discount));
        cart_checkout_total_text.setText(Utils.currency(total));
    }
}