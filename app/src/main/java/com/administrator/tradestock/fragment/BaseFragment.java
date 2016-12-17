package com.administrator.tradestock.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.administrator.tradestock.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    private Toast mInfoToast;

    /**
     * 显示toast对话框
     *
     * @param msg
     */
    public void showToast(CharSequence msg) {
        if (msg == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        } else {
            if (mInfoToast == null) {
                mInfoToast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
            }
            mInfoToast.cancel();
            mInfoToast.setText(msg);
            mInfoToast.show();
        }
    }

}
