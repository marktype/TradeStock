package com.administrator.tradestock.activity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.model.GongGaoBean;
import com.administrator.tradestock.util.HttpManagerUtil;
import com.administrator.tradestock.util.SharePrenceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class NoticeActivity extends BascActivity implements View.OnClickListener{
    private SharedPreferences sp;
    private ArrayList<GongGaoBean> list;
    private ListView mList;

    private ArrayList<String> liststr;   //测试list
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        sp = SharePrenceUtil.getShareSaveUserInfo(this);
        initWidget();

        GongGaoAsyn gongGaoAsyn = new GongGaoAsyn();
        gongGaoAsyn.execute(sp.getString(SharePrenceUtil.OPEN_ID,""));
    }

    private void initWidget(){
        mList = (ListView) findViewById(R.id.notice_list);
        ImageView mBackImg= (ImageView) findViewById(R.id.back_img);

        mBackImg.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
        }
    }

    private class GongGaoAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("openid", strings[0])
                    .build();
            String message = HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody, HttpManagerUtil.PUBLIC_INFO);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)){
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    if (jsonArray != null){
                        list = new ArrayList<>();
                        liststr = new ArrayList<>();
                        for (int i = 0;i<jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            GongGaoBean gongGaoBean = new GongGaoBean();
                            gongGaoBean.setName(object.getString("name"));
                            gongGaoBean.setImg(object.getString("img"));
                            gongGaoBean.setTime(object.getString("time"));
                            list.add(gongGaoBean);
                            liststr.add(object.getString("name"));
                        }
                        adapter = new ArrayAdapter(NoticeActivity.this,R.layout.txt_item_list_two_layout,liststr);
                        mList.setAdapter(adapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                showToast(s);
            }

        }
    }
}
