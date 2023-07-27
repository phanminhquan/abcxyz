package com.mtb.foodorderreview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialization();

        bottomNavigation();
    }

    private void initialization() {
        navigationView = findViewById(R.id.bottom_navigation1);

        fragmentManager = getSupportFragmentManager();

    }

    private void bottomNavigation() {
        int mainFrameId = R.id.main_frame_layout1;

        fragmentManager
                .beginTransaction()
                .replace(mainFrameId, HomePageFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("home") // Name can be null
                .commit();

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.isChecked())
                    return false;

                int id = item.getItemId();

                if (id == R.id.bottom_navigation_home1) {
                    fragmentManager
                            .beginTransaction()
                            .replace(mainFrameId, HomePageFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("home") // Name can be null
                            .commit();
                }

                if (id == R.id.bottom_navigation_order1) {
                    fragmentManager
                            .beginTransaction()
                            .replace(mainFrameId, OrdersFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("order") // Name can be null
                            .commit();
                }

                if (id == R.id.bottom_navigation_profile1) {
                    fragmentManager
                            .beginTransaction()
                            .replace(mainFrameId, ProfileFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("profile") // Name can be null
                            .commit();
                }

                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        // Not calling **super**, disables back button in current screen.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            // Checkout ok
            case 3:
                if (resultCode != Activity.RESULT_OK) return;
                Intent intent = new Intent(this, DeliveryActivity.class);
                startActivity(intent);

                break;
            case 4:
                if (resultCode != Activity.RESULT_OK) return;
//                Intent intent = new Intent(this, DeliveryActivity.class);
//                startActivity(intent);

                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}