package com.administrator.tradestock.activity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.util.HttpManagerUtil;
import com.administrator.tradestock.util.SharePrenceUtil;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RealNameActivity extends BascActivity implements View.OnClickListener{
    private EditText mUserName,mUserCardNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.app_yellow));
        setContentView(R.layout.activity_real_name_layout);
        initWidget();
    }

    private void initWidget(){
        ImageView mBackImg= (ImageView) findViewById(R.id.back_img);
        TextView mNext = (TextView) findViewById(R.id.register_btn);
        mUserName = (EditText) findViewById(R.id.user_name);
        mUserCardNum = (EditText) findViewById(R.id.user_card_num);


        mBackImg.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.register_btn:
                String userName = mUserName.getText().toString().trim();
                String userCard = mUserCardNum.getText().toString().trim();
                if (!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(userCard)){
                    SharedPreferences sp = SharePrenceUtil.getShareSaveUserInfo(this);
                    AddCardAsyn addCardAsyn = new AddCardAsyn();
                    addCardAsyn.execute(userName,userCard,sp.getString(SharePrenceUtil.OPEN_ID,""));
                }else {
                    showToast("请填写姓名或身份证号");
                }

                break;
        }
    }

    /**
     *实名认证
     */
    private class AddCardAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("truename", strings[0])
                    .add("shenfz", strings[1])
                    .add("openid", strings[2])
                    .build();
            String message =  HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody,HttpManagerUtil.REAL_NAME_CHECK);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            showToast(s);
        }
    }
}
