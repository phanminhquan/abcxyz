package com.mtb.foodorderreview;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.mtb.foodorderreview.api.UserService;
import com.mtb.foodorderreview.global.UserGlobal;
import com.mtb.foodorderreview.model.User;
import com.mtb.foodorderreview.utils.Utils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    CardView profile_avatar_cardview;
    ImageView profile_avatar_image;
    TextView profile_id_text,
            profile_username_text;
    EditText profile_name_inp,
            profile_tel_inp,
            profile_email_inp,
            profile_address_inp;
    Button profile_save_btn,
            profile_logout_btn;
    Context context;
    String name,
            tel,
            email,
            address;
    UserGlobal userGlobal = UserGlobal.getInstance();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        context = getContext();

        initialization(view);
        bindData();
        makeChangeListener(profile_name_inp, name);
        makeChangeListener(profile_tel_inp, tel);
        makeChangeListener(profile_email_inp, email);
        makeChangeListener(profile_address_inp, address);
        btnSubmit();
        btnLogout();

        return view;
    }

    private void initialization(View view) {
        profile_avatar_cardview = view.findViewById(R.id.profile_avatar_cardview);
        profile_avatar_image = view.findViewById(R.id.profile_avatar_image);
        profile_id_text = view.findViewById(R.id.profile_id_text);
        profile_username_text = view.findViewById(R.id.profile_username_text);
        profile_name_inp = view.findViewById(R.id.profile_name_inp);
        profile_tel_inp = view.findViewById(R.id.profile_tel_inp);
        profile_email_inp = view.findViewById(R.id.profile_email_inp);
        profile_address_inp = view.findViewById(R.id.profile_address_inp);
        profile_save_btn = view.findViewById(R.id.profile_save_btn);
        profile_logout_btn = view.findViewById(R.id.profile_logout_btn);
    }

    private void btnSubmit() {
        profile_save_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Map<String, Object> map = Map.of(
                        "taiKhoan", profile_username_text.getText().toString(),
                        "ten", profile_name_inp.getText().toString(),
                        "soDienThoai", profile_tel_inp.getText().toString(),
                        "email", profile_email_inp.getText().toString(),
                        "diaChi", profile_address_inp.getText().toString()
                );
                UserService.apiService.update(Integer.parseInt(profile_id_text.getText().toString()), map).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        updateVariables();
                        if ("callApiSuccess" != null) {
                            userGlobal.setName(name);
                            userGlobal.setAddress(address);
                            userGlobal.setEmail(email);
                            userGlobal.setTel(tel);

                            bindData();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
                // Call api here
            }
        });
    }

    private void makeChangeListener(EditText editText, String init) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = s.toString();
                if (str.trim().equals(init)) {
                    Utils.UI.disableBtn(context, profile_save_btn);

                    return;
                }
                Utils.UI.enableBtn(context, profile_save_btn);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void updateVariables() {
        name = profile_name_inp.getText().toString();
        tel = profile_tel_inp.getText().toString();
        email = profile_email_inp.getText().toString();
        address = profile_address_inp.getText().toString();
    }

    private void bindData() {
        name = userGlobal.getName().trim();
        tel = userGlobal.getTel().trim();
        email = userGlobal.getEmail().trim();
        address = userGlobal.getAddress().trim();

        profile_avatar_image.setImageResource(userGlobal.getAvatar());
        profile_id_text.setText(String.valueOf(userGlobal.getId()));
        profile_username_text.setText(userGlobal.getUserName().trim());

        profile_name_inp.clearFocus();
        profile_tel_inp.clearFocus();
        profile_email_inp.clearFocus();
        profile_address_inp.clearFocus();

        profile_name_inp.setText(name);
        profile_tel_inp.setText(tel);
        profile_email_inp.setText(email);
        profile_address_inp.setText(address);

        Utils.UI.disableBtn(context, profile_save_btn);
    }

    private void btnLogout() {
        profile_logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserGlobal.userLogout(context);

                Intent mStartActivity = new Intent(context, MainEmptyActivity.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity,
                        PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
            }
        });
    }
}