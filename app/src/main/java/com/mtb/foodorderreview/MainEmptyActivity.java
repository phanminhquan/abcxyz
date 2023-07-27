package com.mtb.foodorderreview;

import static com.mtb.foodorderreview.global.UserGlobal.USER_DATA_PASSWORD_FILE;
import static com.mtb.foodorderreview.global.UserGlobal.USER_DATA_USERNAME_FILE;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.utils.Utils;

public class MainEmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empty);

        Intent activityIntent;

        String username = Utils.readFile(this, USER_DATA_USERNAME_FILE);
        String pass = Utils.readFile(this, USER_DATA_PASSWORD_FILE);

//        fakeUserFromAPI(username, pass);
        // Call api login here

        if (UserGlobal.getInstance().getUserName() != null) {
            activityIntent = new Intent(this, MainActivity.class);
        } else {
            activityIntent = new Intent(this, AuthActivity.class);
        }

        finish();
        startActivity(activityIntent);
    }

    public static void fakeUserFromAPI(String username, String password) {
        if (username.equals("example") && password.equals("example")) {
            UserGlobal.getInstance().setData(
                    1,
                    username,
                    "Anh Ba",
                    "0123456789",
                    R.drawable.img_sample_user_avatar,
                    "abcxyz123@gmail.com",
                    "Thành Phố HCM",
                    "lakjwdliawjdljia",
                    false);
        }
    }
}