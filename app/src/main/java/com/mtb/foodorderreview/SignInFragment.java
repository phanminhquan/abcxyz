package com.mtb.foodorderreview;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {
    AuthActivity authActivity;
    EditText
            sign_in_username_input,
            sign_in_password_input;
    TextView fragment_sign_in_response_text;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        init(view);

        reAssignValue();

        setOnInputs();

        return view;
    }

    private void init(View v) {
        authActivity = (AuthActivity) getActivity();

        sign_in_username_input = v.findViewById(R.id.sign_in_username_input);
        sign_in_password_input = v.findViewById(R.id.sign_in_password_input);
        fragment_sign_in_response_text = v.findViewById(R.id.fragment_sign_in_response_text);

    }

    private void reAssignValue() {
        sign_in_username_input.setText(authActivity.getString("authUsername"));
        sign_in_password_input.setText(authActivity.getString("authPassword"));
        fragment_sign_in_response_text.setText(authActivity.getString("authResponse"));
    }

    private void setOnInput(EditText editText, String bundleName) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                authActivity.setString(bundleName, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void setOnInputs() {
        setOnInput(sign_in_username_input, "authUsername");
        setOnInput(sign_in_password_input, "authPassword");
    }
}