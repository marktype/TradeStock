package com.administrator.tradestock.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.customview.AuthCodeView;
import com.administrator.tradestock.util.HttpManagerUtil;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LoginActivity extends BascActivity implements View.OnClickListener{
    private EditText mUserPhone,mUserPassword,mVerify;
    private AuthCodeView mauthCodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.app_yellow));
        setContentView(R.layout.activity_login);

        initWidget();
    }



    private void initWidget(){
        TextView mLogin = (TextView) findViewById(R.id.login_btn);
        TextView mRegister = (TextView) findViewById(R.id.register_btn);
        TextView mforgetPassword = (TextView) findViewById(R.id.forget_password_btn);
        mUserPhone = (EditText) findViewById(R.id.user_phone);
        mUserPassword = (EditText) findViewById(R.id.user_password);
        mauthCodeView = (AuthCodeView)findViewById(R.id.AuthCodeView);
        mVerify = (EditText) findViewById(R.id.verify_num);

        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mforgetPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                String phone = mUserPhone.getText().toString().trim();
                String password = mUserPassword.getText().toString().trim();
                String codeString = mVerify.getText().toString().trim();
                if (!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(password)&&codeString != null&&codeString.equals(mauthCodeView.getAuthCode())){
                    LoginAsyn loginAsyn = new LoginAsyn();
                    loginAsyn.execute(phone,password);

                }else if (codeString == null||!codeString.equals(mauthCodeView.getAuthCode())){
                    showToast("请填写正确的验证码");
                }else {
                    showToast("请输入手机号或密码");
                }
                break;
            case R.id.register_btn:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.forget_password_btn:
                intent = new Intent(this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     *登陆
     */
    private class LoginAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("mobile", strings[0])
                    .add("password", strings[1])
                    .build();
            String message =  HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody,HttpManagerUtil.LOGIN);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //测试
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

            //正式
//            if (s.contains("status")&&s.contains("openid")){
//                showToast("登陆成功");
//                SharedPreferences sp = SharePrenceUtil.getShareSaveUserInfo(LoginActivity.this);
//                SharedPreferences.Editor editor = sp.edit();
//                try {
//                    JSONObject object = new JSONObject(s);
//                    String opennid = object.getString("openid");
//                    editor.putString(SharePrenceUtil.OPEN_ID,opennid);
//                    editor.commit();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }else if (s.contains("status")){
//                showToast("登陆失败");
//            }else {
//                showToast(s);
//            }

        }
    }
}
