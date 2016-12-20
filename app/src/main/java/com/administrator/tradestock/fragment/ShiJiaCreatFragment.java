package com.administrator.tradestock.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.administrator.tradestock.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShiJiaCreatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShiJiaCreatFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;
    private EditText mEditPoint,mHandNum;

    public ShiJiaCreatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShiJiaCreatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShiJiaCreatFragment newInstance(String param1, String param2) {
        ShiJiaCreatFragment fragment = new ShiJiaCreatFragment();
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
            mView = inflater.inflate(R.layout.fragment_shi_jia_creat, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        CheckBox mCheck = (CheckBox) mView.findViewById(R.id.trade_checked);
        mEditPoint = (EditText) mView.findViewById(R.id.edit_point);
        TextView mClearData = (TextView) mView.findViewById(R.id.clear_data);
        TextView mPlaceOrder = (TextView) mView.findViewById(R.id.place_order);
        TextView mOncePlaceOrder = (TextView) mView.findViewById(R.id.once_place_order);
        mHandNum = (EditText) mView.findViewById(R.id.hand_num);
        TextView mMaxNum = (TextView) mView.findViewById(R.id.max_num);


        mEditPoint.setEnabled(false);
        mEditPoint.setText("");
        mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

        mClearData.setOnClickListener(this);
        mPlaceOrder.setOnClickListener(this);
        mOncePlaceOrder.setOnClickListener(this);
        mMaxNum.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.clear_data:
                mHandNum.setText("");
                mEditPoint.setText("");
                break;
            case R.id.place_order:
                break;
            case R.id.once_place_order:
                break;
            case R.id.max_num:
                break;
        }
    }
}
