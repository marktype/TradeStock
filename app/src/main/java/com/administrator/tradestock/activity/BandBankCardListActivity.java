package com.administrator.tradestock.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.adapter.BankCardAdapter;
import com.administrator.tradestock.model.BankInfoBean;
import com.administrator.tradestock.util.HttpManagerUtil;
import com.administrator.tradestock.util.SharePrenceUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class BandBankCardListActivity extends BascActivity implements View.OnClickListener{
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = SharePrenceUtil.getShareSaveUserInfo(this);
        setContentView(R.layout.bank_list_layout);
        initWidget();
        Log.d("tag","11111111111111111");
        CardListAsyn cardListAsyn = new CardListAsyn();
        cardListAsyn.execute(sp.getString(SharePrenceUtil.OPEN_ID,""));
    }

    private void initWidget(){
        ImageView mBackImg= (ImageView) findViewById(R.id.back_img);
        TextView mAddCard = (TextView) findViewById(R.id.add_card_txt);
        ListView mList = (ListView) findViewById(R.id.bank_card_list);
        BankCardAdapter adapter = new BankCardAdapter(this);
        adapter.setData(getBankData());
        mList.setAdapter(adapter);

        mBackImg.setOnClickListener(this);
        mAddCard.setOnClickListener(this);

    }

    private  List<BankInfoBean> getBankData(){
        List<BankInfoBean> list = new ArrayList<>();
        for (int i =0;i<2;i++){
            BankInfoBean infoBean = new BankInfoBean();
            infoBean.setBankName("中国银行");
            infoBean.setBankNum("123464598798787987");
            list.add(infoBean);
        }
        return list;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.add_card_txt:
                Intent intent= new Intent(this,BandBankCardActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 银行卡列表
     */
    private class CardListAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("openid", strings[0])
                    .build();
            String message = HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody, HttpManagerUtil.MY_CARD_LIST);
            return message;
        }

        //{"min":"361.000","max":"361.000"}
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)&&s.contains("smrz_false")){
                showToast("未进行实名认证");
            }else if (!TextUtils.isEmpty(s)&&s.contains("bank_list")){

            }else {
                showToast(s);
            }

        }
    }

}
