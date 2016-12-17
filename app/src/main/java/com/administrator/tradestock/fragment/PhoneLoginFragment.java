package com.administrator.tradestock.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.activity.ForgetPasswordActivity;
import com.administrator.tradestock.activity.MainActivity;
import com.administrator.tradestock.activity.RegisterActivity;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhoneLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhoneLoginFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;

    public PhoneLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhoneLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhoneLoginFragment newInstance(String param1, String param2) {
        PhoneLoginFragment fragment = new PhoneLoginFragment();
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
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_phone_login, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        TextView mLogin = (TextView) mView.findViewById(R.id.login_btn);
        TextView mRegister = (TextView) mView.findViewById(R.id.register_btn);
        TextView mExit = (TextView) mView.findViewById(R.id.exit_btn);
        TextView mforgetPassword = (TextView) mView.findViewById(R.id.forget_password_btn);

        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mExit.setOnClickListener(this);
        mforgetPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.register_btn:
                intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.exit_btn:
                break;
            case R.id.forget_password_btn:
                intent = new Intent(getContext(), ForgetPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
