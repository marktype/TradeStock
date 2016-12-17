package com.administrator.tradestock.activity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.TradeApplication;
import com.administrator.tradestock.model.UserRegisterInfo;
import com.administrator.tradestock.util.Contants;
import com.administrator.tradestock.util.HttpManagerUtil;
import com.administrator.tradestock.util.SharePrenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * 用户约定
 */
public class CompanyDefinedActivity extends BascActivity implements View.OnClickListener{
    private CheckBox mCheckBox;
    private UserRegisterInfo userRegisterInfo;
    private TradeApplication main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_defined);
        userRegisterInfo = getIntent().getParcelableExtra(Contants.REGISTR_INFO);
        main = TradeApplication.getInstance();
        main.addActivity(this);
        initWidget();
    }

    private void initWidget(){
        ImageView mBackImg= (ImageView) findViewById(R.id.back_img);
        TextView mContinue = (TextView) findViewById(R.id.agree_and_continue);
        mCheckBox = (CheckBox) findViewById(R.id.check_btn);

        mBackImg.setOnClickListener(this);
        mContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.agree_and_continue:
                if (mCheckBox.isChecked()){
                    RegisterAsyn registerAsyn = new RegisterAsyn();
                    registerAsyn.execute(userRegisterInfo.getPhone(),userRegisterInfo.getPassword(),userRegisterInfo.getUsername(),userRegisterInfo.getSms());
                }else {
                    showToast("请先同意协议再注册");
                }
                break;
        }
    }


    /**
     *注册
     */
    private class RegisterAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("mobile", strings[0])
                    .add("password", strings[1])
                    .add("truename", strings[2])
                    .add("agent_id", "21155")
                    .add("sever_rand", strings[3])
                    .add("broker_reg", "")
                    .add("OPENID_WX", "")
                    .build();
            String message =  HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody,HttpManagerUtil.REGISTER);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.contains("status")&&s.contains("openid")){
                showToast("注册成功");
                SharedPreferences sp = SharePrenceUtil.getShareSaveUserInfo(CompanyDefinedActivity.this);
                SharedPreferences.Editor editor = sp.edit();
                try {
                    JSONObject object = new JSONObject(s);
                    String opennid = object.getString("openid");
                    editor.putString(SharePrenceUtil.OPEN_ID,opennid);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                main.finishAll();
            }else {
                showToast("注册失败");
            }
        }
    }
}
