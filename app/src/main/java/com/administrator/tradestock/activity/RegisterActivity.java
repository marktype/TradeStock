package com.administrator.tradestock.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.TradeApplication;
import com.administrator.tradestock.model.UserRegisterInfo;
import com.administrator.tradestock.util.Contants;
import com.administrator.tradestock.util.HttpManagerUtil;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RegisterActivity extends BascActivity implements View.OnClickListener{
    private TextView mGetVerify;
    private EditText mPasswordOne,mPasswordTwo,mPhone,mUserName,mVerify;
    private String passwordOne,passwordTwo,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.app_yellow));
        setContentView(R.layout.activity_register);
        TradeApplication main = TradeApplication.getInstance();
        main.addActivity(this);
        initWidget();
    }

    private void initWidget(){
        ImageView mBackImg= (ImageView) findViewById(R.id.back_img);
        TextView mRegister = (TextView) findViewById(R.id.register_btn);
        mGetVerify = (TextView) findViewById(R.id.get_sms);
        mPasswordOne = (EditText) findViewById(R.id.new_password_one);
        mPasswordTwo = (EditText) findViewById(R.id.new_password_two);
        mPhone = (EditText) findViewById(R.id.edit_phone);
        mUserName = (EditText) findViewById(R.id.username);
        mVerify = (EditText) findViewById(R.id.verify_sms);

        mBackImg.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mGetVerify.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.register_btn:
                String username = mUserName.getText().toString().trim();
                String verify = mVerify.getText().toString().trim();
                if (!TextUtils.isEmpty(passwordOne)&&!TextUtils.isEmpty(passwordTwo)&&
                        !TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(verify)){
                    UserRegisterInfo userRegisterInfo = new UserRegisterInfo();
                    userRegisterInfo.setPassword(passwordOne);
                    userRegisterInfo.setUsername(username);
                    userRegisterInfo.setPhone(phone);
                    userRegisterInfo.setSms(verify);
                    Intent intent = new Intent(this,CompanyDefinedActivity.class);
                    intent.putExtra(Contants.REGISTR_INFO,userRegisterInfo);
                    startActivity(intent);
                }else if (TextUtils.isEmpty(passwordOne)&&TextUtils.isEmpty(passwordTwo)&&
                        TextUtils.isEmpty(phone)){
                    showToast("请填写正确的验证码");
                }else {
                    showToast("请填写完整信息");
                }
                break;
            case R.id.get_sms:
                passwordOne = mPasswordOne.getText().toString().trim();
                passwordTwo = mPasswordTwo.getText().toString().trim();
                phone = mPhone.getText().toString().trim();
                if (passwordOne == null||passwordOne.length()<6||!passwordOne.equals(passwordTwo)){
                    showToast("密码长度小于6或者两次密码输入不一致");
                }else if (phone == null||phone.length() != 11){
                    showToast("手机号输入不正确");
                }else {
                    SmsAsyn smsAsyn = new SmsAsyn();
                    smsAsyn.execute(phone);
                }

                break;
        }
    }


    /**
     *短信验证
     */
    private class SmsAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("mobile", strings[0])
                    .build();
            String message =  HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody,HttpManagerUtil.INFO_CHECK);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
                TimeCount time = new TimeCount(60000, 1000);
                time.start();// 开始计时
        }
    }

    /**
     * 倒计时
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            mGetVerify.setText("发送验证码");
            mGetVerify.setEnabled(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            mGetVerify.setEnabled(false);
            mGetVerify.setText(millisUntilFinished / 1000+"s后重新发送");
        }
    }
}
