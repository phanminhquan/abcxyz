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

import com.mtb.foodorderreview.api.DonHangService;
import com.mtb.foodorderreview.global.OrderGlobal;
import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.model.DonHang;
import com.mtb.foodorderreview.something.Order;
import com.mtb.foodorderreview.utils.Utils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryActivity extends AppCompatActivity {
    View delivery_fake_line_prepare,
            delivery_fake_line_shipping,
            delivery_fake_line_shipped;
    Button delivery_fake_submit_btn;
    ImageView delivery_icon_image_prepare,
            delivery_icon_image_shipping,
            delivery_icon_image_shipped;
    TextView delivery_status_text,
            delivery_restaurant_text,
            delivery_restaurant_address_text,
            delivery_delivery_location_text,
            delivery_delivery_location_address_text;
    LinearLayout delivery_back_btn;

    Order order = OrderGlobal.getInstance().getOrder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        initialization();
        Utils.UI.backBtn(this, delivery_back_btn);

        submitBtnClick();
        updateUI(order.getState());

//        watchOrder();

    }

    private void watchOrder() {
//        if (order.getState() == Order.STATE.PENDING) {
//            delivery_fake_submit_btn.setEnabled(false);
//            Utils.UI.setBackgroundTint(this, delivery_fake_submit_btn, R.color.grey_3);
//            return;
//        }

        if ("callApiToCreateOrderSuccess" != null) {

            int idSample = -1;
            Order.STATE stateSample = Order.STATE.DELIVERING;
            order.setId(idSample);
            order.setState(stateSample);

            updateUI(order.getState());

            delivery_fake_submit_btn.setEnabled(true);
//            delivery_fake_submit_btn.setBackgroundResource( R.color.primary);
            Utils.UI.setBackgroundTint(this, delivery_fake_submit_btn, R.color.primary);
        }
    }


    private void initialization() {
        delivery_fake_line_prepare = findViewById(R.id.delivery_fake_line_prepare);
        delivery_fake_line_shipping = findViewById(R.id.delivery_fake_line_shipping);
        delivery_fake_line_shipped = findViewById(R.id.delivery_fake_line_shipped);
        delivery_fake_submit_btn = findViewById(R.id.delivery_fake_submit_btn);
        delivery_icon_image_prepare = findViewById(R.id.delivery_icon_image_prepare);
        delivery_icon_image_shipping = findViewById(R.id.delivery_icon_image_shipping);
        delivery_icon_image_shipped = findViewById(R.id.delivery_icon_image_shipped);
        delivery_status_text = findViewById(R.id.delivery_status_text);
        delivery_restaurant_text = findViewById(R.id.delivery_restaurant_text);
        delivery_restaurant_address_text = findViewById(R.id.delivery_restaurant_address_text);
        delivery_delivery_location_text = findViewById(R.id.delivery_delivery_location_text);
        delivery_delivery_location_address_text = findViewById(R.id.delivery_delivery_location_address_text);
        delivery_back_btn = findViewById(R.id.delivery_back_btn);

        delivery_restaurant_text.setText(order.getRestaurant().getName());
        delivery_restaurant_address_text.setText(order.getRestaurant().getAddress());
        delivery_delivery_location_address_text.setText(UserGlobal.getInstance().getAddress());

    }

    public void updateUI(Order.STATE state) {
        switch (state) {
            case PENDING:
                break;
            case PREPARING:
                updateUIPreparing();
            case DELIVERING:
                updateUIDelivering();
                break;
            case DELIVERED:
                updateUIDelivered();
                break;
        }
    }


    private void updateUIPreparing() {
        delivery_status_text.setText("Nhà hàng đang chuẩn bị");
        delivery_fake_line_prepare.setBackgroundResource(R.color.primary);

        Utils.UI.setBackgroundTint(this, delivery_icon_image_prepare, R.color.primary);
        delivery_icon_image_prepare.setBackgroundResource(R.drawable.shape_border_box_primary);

    }

    private void updateUIDelivering() {
        updateUIPreparing();

        delivery_status_text.setText("Shipper đang giao");
        delivery_fake_line_shipping.setBackgroundResource(R.color.primary);
        Utils.UI.setBackgroundTint(this, delivery_icon_image_shipping, R.color.primary);
        delivery_icon_image_shipping.setBackgroundResource(R.drawable.shape_border_box_primary);
    }

    private void updateUIDelivered() {
        updateUIDelivering();

        delivery_status_text.setText("Đã giao tới");
        delivery_fake_line_shipped.setBackgroundResource(R.color.primary);
        Utils.UI.setBackgroundTint(this, delivery_icon_image_shipped, R.color.primary);
        delivery_icon_image_shipped.setBackgroundResource(R.drawable.shape_border_box_primary);
    }

    private void submitBtnClick() {
        delivery_fake_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUIDelivered();

                order.setState(Order.STATE.DELIVERED);
                // Call api here

                Bundle bundle = getIntent().getExtras();
                int id = order.getId();
//                int id = bundle.getInt("idDonhang");
                Map<String, Object> map = Map.of(
                        "trangThai", "4"
                );

                DonHangService.apiService.updateST(id, map).enqueue(new Callback<DonHang>() {
                    @Override
                    public void onResponse(Call<DonHang> call, Response<DonHang> response) {
                        OrderGlobal.getInstance().reset();
                        Intent intent = new Intent();
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<DonHang> call, Throwable t) {

                    }
                });


            }
        });
    }
}