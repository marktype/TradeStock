package com.administrator.tradestock.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.administrator.tradestock.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShiJiaPingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShiJiaPingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;
    private EditText mEditPoint,mEditBack;


    public ShiJiaPingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShiJiaPingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShiJiaPingFragment newInstance(String param1, String param2) {
        ShiJiaPingFragment fragment = new ShiJiaPingFragment();
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
            mView = inflater.inflate(R.layout.fragment_shi_jia_ping, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        CheckBox mCheckPoint = (CheckBox) mView.findViewById(R.id.check_point);
        CheckBox mCheckBack = (CheckBox) mView.findViewById(R.id.check_back);
        mEditPoint = (EditText) mView.findViewById(R.id.edit_point);
        mEditBack = (EditText) mView.findViewById(R.id.edit_back);


        mEditPoint.setEnabled(false);
        mEditPoint.setText("");
        mEditBack.setEnabled(false);
        mEditBack.setText("");
        mCheckPoint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mEditPoint.setEnabled(true);
                }else {
                    mEditPoint.setEnabled(false);
                    mEditPoint.setText("");
                }
            }
        });
        mCheckBack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mEditBack.setEnabled(true);
                }else {
                    mEditBack.setEnabled(false);
                    mEditBack.setText("");
                }
            }
        });

    }



}
