package com.administrator.tradestock.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.activity.AlterPasswordActivity;
import com.administrator.tradestock.activity.BandBankCardActivity;
import com.administrator.tradestock.activity.MyMoenyListActivity;
import com.administrator.tradestock.activity.RealNameActivity;
import com.administrator.tradestock.activity.TradeListActivity;
import com.administrator.tradestock.util.SharePrenceUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{

    private View mView;
    private TextView mUserName,mUserPhone,mUserMoney;


    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_my_layout, container, false);
            initWidget();
            initUserData();
        }
        return mView;
    }

    private void initWidget(){
        RelativeLayout mMoney = (RelativeLayout) mView.findViewById(R.id.manager_money);
        RelativeLayout mRecord = (RelativeLayout) mView.findViewById(R.id.trade_record);
        RelativeLayout mAlterPassword = (RelativeLayout) mView.findViewById(R.id.alter_password);
        RelativeLayout mZhuXiao = (RelativeLayout) mView.findViewById(R.id.zhuxiao_system);
        RelativeLayout mBandCard = (RelativeLayout) mView.findViewById(R.id.band_bank_card);
        RelativeLayout mRealName = (RelativeLayout) mView.findViewById(R.id.real_name);
        mUserName = (TextView) mView.findViewById(R.id.user_name);
        mUserPhone = (TextView) mView.findViewById(R.id.user_phone);
        mUserMoney = (TextView) mView.findViewById(R.id.user_money);


        mMoney.setOnClickListener(this);
        mRecord.setOnClickListener(this);
        mAlterPassword.setOnClickListener(this);
        mZhuXiao.setOnClickListener(this);
        mBandCard.setOnClickListener(this);
        mRealName.setOnClickListener(this);
    }

    /**
     * 初始化用户数据
     */
    private void initUserData(){
        SharedPreferences sp = SharePrenceUtil.getShareSaveUserInfo(getContext());
        mUserName.setText("昵称："+sp.getString(SharePrenceUtil.NAME,""));
        mUserPhone.setText("电话："+sp.getString(SharePrenceUtil.USER_PHONE,""));
        mUserMoney.setText("￥"+sp.getString(SharePrenceUtil.YU_E,""));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.manager_money:
                Intent intent = new Intent(getContext(),MyMoenyListActivity.class);
                startActivity(intent);
                break;
            case R.id.trade_record:
                intent = new Intent(getContext(),TradeListActivity.class);
                startActivity(intent);
                break;
            case R.id.alter_password:
                intent = new Intent(getContext(),AlterPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.zhuxiao_system:
                getActivity().finish();
                break;
            case R.id.band_bank_card:
                intent = new Intent(getContext(),BandBankCardActivity.class);
                startActivity(intent);
                break;
            case R.id.real_name:
                intent = new Intent(getContext(),RealNameActivity.class);
                startActivity(intent);
                break;

        }
    }
}
