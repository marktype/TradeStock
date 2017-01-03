package com.administrator.tradestock.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.activity.AlterPasswordActivity;
import com.administrator.tradestock.activity.BandBankCardListActivity;
import com.administrator.tradestock.activity.MyMoenyListActivity;
import com.administrator.tradestock.activity.NoticeActivity;
import com.administrator.tradestock.activity.RealNameActivity;
import com.administrator.tradestock.activity.TradeListActivity;
import com.administrator.tradestock.model.UserInfo;
import com.administrator.tradestock.util.HttpManagerUtil;
import com.administrator.tradestock.util.SharePrenceUtil;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.RequestBody;

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
            UserInfoAsyn userInfoAsyn = new UserInfoAsyn();
            userInfoAsyn.execute(SharePrenceUtil.getShareSaveUserInfo(getContext()).getString(SharePrenceUtil.OPEN_ID,""));
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
        RelativeLayout mReadPub = (RelativeLayout) mView.findViewById(R.id.read_public);

        mUserName = (TextView) mView.findViewById(R.id.user_name);
        mUserPhone = (TextView) mView.findViewById(R.id.user_phone);
        mUserMoney = (TextView) mView.findViewById(R.id.user_money);


        mMoney.setOnClickListener(this);
        mRecord.setOnClickListener(this);
        mAlterPassword.setOnClickListener(this);
        mZhuXiao.setOnClickListener(this);
        mBandCard.setOnClickListener(this);
        mRealName.setOnClickListener(this);
        mReadPub.setOnClickListener(this);
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
                intent = new Intent(getContext(),BandBankCardListActivity.class);
                startActivity(intent);
                break;
            case R.id.real_name:
                intent = new Intent(getContext(),RealNameActivity.class);
                startActivity(intent);
                break;
            case R.id.read_public:
                intent = new Intent(getContext(),NoticeActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 首页用户商品信息
     */
    private class UserInfoAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("openid", strings[0])
                    .build();
            String message = HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody, HttpManagerUtil.USER_INFO);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                UserInfo info = gson.fromJson(s,UserInfo.class);
                parseUserInfo(info);
            }

        }
    }
    private void parseUserInfo(UserInfo info) {
        mUserName.setText("昵称："+info.getTruename());
        mUserPhone.setText("电话："+info.getMobile());
        mUserMoney.setText("￥"+info.getMoney());
    }

}
