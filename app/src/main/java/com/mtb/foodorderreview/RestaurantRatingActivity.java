package com.mtb.foodorderreview;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.api.CommentService;
import com.mtb.foodorderreview.components.ExpandableHeightListView;
import com.mtb.foodorderreview.global.RestaurantGlobal;
import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.model.DanhGia;
import com.mtb.foodorderreview.restaurentview.RestaurantUserRate;
import com.mtb.foodorderreview.restaurentview.RestaurantUserRateListViewAdapter;
import com.mtb.foodorderreview.utils.ICallback;
import com.mtb.foodorderreview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRatingActivity extends AppCompatActivity {
    ExpandableHeightListView restaurant_rating_listview;

    LinearLayout restaurant_rating_back_btn;

    ImageView restaurant_rating_star_1_image,
            restaurant_rating_star_2_image,
            restaurant_rating_star_3_image,
            restaurant_rating_star_4_image,
            restaurant_rating_star_5_image;

    EditText restaurant_rating_note_input;

    Button restaurant_rating_submit_btn;
    int starCount = 0;
    String note = "";
    RestaurantUserRateListViewAdapter adapter;
    List<RestaurantUserRate> l;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_rating);

        context = RestaurantRatingActivity.this;

        initialization();

        setStar(starCount);
        starsClick();
        userNote();
        rateSubmit();
        updateSubmitBtn();

        listview();

        Utils.UI.backBtn(this, restaurant_rating_back_btn);
    }

    private void initialization() {
        restaurant_rating_listview = findViewById(R.id.restaurant_rating_listview);
        restaurant_rating_back_btn = findViewById(R.id.restaurant_rating_back_btn);
        restaurant_rating_star_1_image = findViewById(R.id.restaurant_rating_star_1_image);
        restaurant_rating_star_2_image = findViewById(R.id.restaurant_rating_star_2_image);
        restaurant_rating_star_3_image = findViewById(R.id.restaurant_rating_star_3_image);
        restaurant_rating_star_4_image = findViewById(R.id.restaurant_rating_star_4_image);
        restaurant_rating_star_5_image = findViewById(R.id.restaurant_rating_star_5_image);
        restaurant_rating_note_input = findViewById(R.id.restaurant_rating_note_input);
        restaurant_rating_submit_btn = findViewById(R.id.restaurant_rating_submit_btn);
    }

    private void activeStar(ImageView star) {
        Utils.UI.setBackgroundTint(context, star, R.color.yellow);
        Utils.UI.setSrc(R.drawable.icon_star_fill, star);
    }

    private void deActiveStar(ImageView star) {
        Utils.UI.setBackgroundTint(context, star, R.color.grey_3);
        Utils.UI.setSrc(R.drawable.icon_star, star);
    }

    public void setStar(int count) {
        deActiveStar(restaurant_rating_star_1_image);
        deActiveStar(restaurant_rating_star_2_image);
        deActiveStar(restaurant_rating_star_3_image);
        deActiveStar(restaurant_rating_star_4_image);
        deActiveStar(restaurant_rating_star_5_image);

        switch (count) {
            case 5:
                activeStar(restaurant_rating_star_5_image);
            case 4:
                activeStar(restaurant_rating_star_4_image);
            case 3:
                activeStar(restaurant_rating_star_3_image);
            case 2:
                activeStar(restaurant_rating_star_2_image);
            case 1:
                activeStar(restaurant_rating_star_1_image);
        }
    }

    private void listview() {

//        List<RestaurantUserRate> listfo = new ArrayList<>();
        CommentService.apiService.getListDanhGia(RestaurantGlobal.getInstance().getRestaurant().getId()).enqueue(new Callback<List<DanhGia>>() {
            @Override
            public void onResponse(Call<List<DanhGia>> call, Response<List<DanhGia>> response) {
                List<DanhGia> list = response.body();
                for (DanhGia d : list) {
                    l.add(new RestaurantUserRate(UserGlobal.getInstance().getName(), d.getRate(), d.getNoiDung()));
                }
                adapter = new RestaurantUserRateListViewAdapter(context, l);
                restaurant_rating_listview.setAdapter(adapter);
                restaurant_rating_listview.setExpanded(true);
            }

            @Override
            public void onFailure(Call<List<DanhGia>> call, Throwable t) {

            }
        });
        l = new ArrayList<>();
//
//        adapter = new RestaurantUserRateListViewAdapter(context, l);
//        restaurant_rating_listview.setAdapter(adapter);
    }

    private void starsClick() {
        starClick(restaurant_rating_star_1_image, 1);
        starClick(restaurant_rating_star_2_image, 2);
        starClick(restaurant_rating_star_3_image, 3);
        starClick(restaurant_rating_star_4_image, 4);
        starClick(restaurant_rating_star_5_image, 5);
    }

    private void starClick(ImageView star, int count) {
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starCount = count == starCount ? 0 : count;

                setStar(starCount);
                updateSubmitBtn();
            }
        });
    }

    private void userNote() {
        restaurant_rating_note_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                note = s.toString().trim();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void rateSubmit() {
        restaurant_rating_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (starCount == 0) return;
                Utils.UI.disableBtn(context, restaurant_rating_submit_btn);
                onUserRateSubmit();
            }
        });
    }

    private void reset() {
        starCount = 0;
        note = "";
        setStar(starCount);
        restaurant_rating_note_input.setText(note);
    }

    private void updateSubmitBtn() {
        if (starCount == 0) {
            Utils.UI.disableBtn(context, restaurant_rating_submit_btn);
            return;
        }
        Utils.UI.enableBtn(context, restaurant_rating_submit_btn);
    }

    private ICallback callback;

    private void onUserRateSubmit() {
        // API here
        DanhGia danhGia = new DanhGia(null, note, null, starCount, UserGlobal.getInstance().getId(), RestaurantGlobal.getInstance().getRestaurant().getId());
        CommentService.apiService.createComment(danhGia).enqueue(new Callback<DanhGia>() {
            @Override
            public void onResponse(Call<DanhGia> call, Response<DanhGia> response) {
                callback.callback();
            }

            @Override
            public void onFailure(Call<DanhGia> call, Throwable t) {

            }
        });
        callback = new ICallback() {
            @Override
            public void callback() {
                l.add(0, new RestaurantUserRate(UserGlobal.getInstance().getName(), starCount, note));
                adapter.notifyDataSetChanged();
                reset();
                //Utils.UI.enableBtn(context, restaurant_rating_submit_btn);
            }
        };
    }
}