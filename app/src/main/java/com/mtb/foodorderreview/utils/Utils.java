package com.mtb.foodorderreview.utils;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.mtb.foodorderreview.MainEmptyActivity;
import com.mtb.foodorderreview.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static final String ip = "http://192.168.1.8:8085/";
    public static final String CURRENCY = "đ";
    public static final String URL_SAMPLE_IMAGE = "https://hellothucung.com/wp-content/uploads/2022/01/Poodle-mau-vang-mo-2.jpg";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void writeFile(Context context, String fileName, String text) {
        try {
            FileOutputStream outputStream = context.openFileOutput(fileName, context.MODE_APPEND);

            outputStream.write(text.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        } catch (Exception e) {
        }
    }

    public static void deleteFile(Context context, String fileName) {
        File dir = context.getFilesDir();
        File file = new File(dir, fileName);
        file.delete();
    }

    public static String readFile(Context context, String fileName) {
        try {
            FileInputStream inputStream = null;
            inputStream = context.openFileInput(fileName);

            int c;
            StringBuilder temp = new StringBuilder();

            while ((c = inputStream.read()) != -1) {
                temp.append((char) c);
            }

            inputStream.close();
            return temp.toString();
        } catch (Exception e) {
        }

        return "";
    }

    public static String currency(double number) {
        String format = "%,.2f";

        return String.format(Locale.getDefault(), format, number) + CURRENCY;
    }

    public static String currency(int number) {
        String format = "%,d";

        return String.format(Locale.getDefault(), format, number) + CURRENCY;
    }

    public static String dateStr(Date date) {
        return simpleDateFormat.format(date);
    }

    public static Date dateParse(String date) throws ParseException {
        return simpleDateFormat.parse(date);
    }

    public static void restartApp(Context context) {
        Intent mStartActivity = new Intent(context, MainEmptyActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 300, mPendingIntent);
        System.exit(0);
    }

    public static class UI {
        public static void backBtn(Context context, LinearLayout btn) {
            btn.setOnClickListener(v -> ((Activity) context).finish());
        }

        public static void setBackgroundTint(Context context, Button button, int id) {
            button.setBackgroundTintList(ContextCompat.getColorStateList(context, id));
        }

        public static void setBackgroundTint(Context context, Button button) {
            button.setBackgroundTintList(null);
        }

        public static void setBackgroundTint(Context context, LinearLayout button, int id) {
            button.setBackgroundTintList(ContextCompat.getColorStateList(context, id));
        }

        public static void setBackgroundTint(Context context, ImageView img, int id) {
            img.setColorFilter(ContextCompat.getColor(context, id), android.graphics.PorterDuff.Mode.SRC_IN);
        }

        public static void setSrc(String url, ImageView imageView) {
            Picasso.get().load(url).error(R.drawable.icon_user).into(imageView);
        }

        public static void setSrc(int src, ImageView img) {
            img.setImageResource(src);
        }

        public static void disableBtn(Context context, Button btn) {
            btn.setEnabled(false);
            setBackgroundTint(context, btn, R.color.grey_3);
        }

        public static void enableBtn(Context context, Button btn) {
            btn.setEnabled(true);
            setBackgroundTint(context, btn);
        }
    }
}