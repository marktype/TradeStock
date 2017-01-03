package com.administrator.tradestock.fragment;


import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.adapter.GoodsTypeAdapter;
import com.administrator.tradestock.model.BuyOrNoInfo;
import com.administrator.tradestock.model.FuDongBean;
import com.administrator.tradestock.model.GoodTypeBean;
import com.administrator.tradestock.model.GoodsInfo;
import com.administrator.tradestock.model.TitleInfo;
import com.administrator.tradestock.util.HttpManagerUtil;
import com.administrator.tradestock.util.SharePrenceUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class TradeFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private View mView;
    private RadioButton mShiJiaBtn, mShiJiaPing, mZhiJiaBtn, mZhiJiaPing;
    private ShiJiaCreatFragment firstFragment;
    private ShiJiaPingFragment secondFragment;
    private ZhiJiaCreatFragment thirdFragment;
    private ZhiJiaPingFragment fourFragment;
    private TextView mTitle,mYue,mUserName,mHigh,mLow,mBuyNum,mBuyNoNum,mFuDong;
    private List<TitleInfo> goodsTypeList;
    private SharedPreferences sp;
    private int mMaxNum;
    private String proCode;
    private ApplyHttpThread thread;
    private FuDongHttpThread fudongthread;
    private int type;   //资源类型
    private String codeString;    //请求类型
    private double price;   //买入卖出价格
    private LinearLayout mBuyLin,mBuyNoLin;




    public TradeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            sp = SharePrenceUtil.getShareSaveUserInfo(getContext());
            mView = inflater.inflate(R.layout.trade_fragment_layout, container, false);
            initViews();
            TitleInfoAsyn titleInfoAsyn = new TitleInfoAsyn();
            titleInfoAsyn.execute();
        }
        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
        isNow = true;
        if (thread == null){
            thread = new ApplyHttpThread();
            thread.start();
            fudongthread = new FuDongHttpThread();
            fudongthread.start();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        isNow = false;
        thread = null;
    }
    //初始化  各种个 View
    private void initViews() {
        RadioGroup mGroup = (RadioGroup) mView.findViewById(R.id.trade_group);
        mShiJiaBtn = (RadioButton) mView.findViewById(R.id.shijia_creat);
        mShiJiaPing = (RadioButton) mView.findViewById(R.id.shijia_ping);
        mZhiJiaBtn = (RadioButton) mView.findViewById(R.id.zhijia_creat);
        mZhiJiaPing = (RadioButton) mView.findViewById(R.id.zhijia_ping);
        mShiJiaBtn.setChecked(true);
        mTitle = (TextView) mView.findViewById(R.id.title_name);
        mYue = (TextView) mView.findViewById(R.id.yue_num);
        mUserName = (TextView) mView.findViewById(R.id.user_name);
        mHigh = (TextView) mView.findViewById(R.id.max_high);
        mLow = (TextView) mView.findViewById(R.id.max_low);
        mBuyNum = (TextView) mView.findViewById(R.id.buy_num);
        mBuyNoNum = (TextView) mView.findViewById(R.id.buy_no_num);
        mFuDong = (TextView) mView.findViewById(R.id.fudong_num_txt);

        mBuyLin = (LinearLayout) mView.findViewById(R.id.buy_layout);
        mBuyNoLin = (LinearLayout) mView.findViewById(R.id.buy_no_layout);

        mTitle.setOnClickListener(this);
        mGroup.setOnCheckedChangeListener(this);
    }

    //隐藏所有Fragment
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (firstFragment != null) {
            fragmentTransaction.hide(firstFragment);
        }
        if (secondFragment != null) {
            fragmentTransaction.hide(secondFragment);
        }
        if (thirdFragment != null) {
            fragmentTransaction.hide(thirdFragment);
        }
        if (fourFragment != null) {
            fragmentTransaction.hide(fourFragment);
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentManager fm = getFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务
        hidtFragment(ft);   //先隐藏 Fragment
        switch (i) {
            case R.id.shijia_creat:
                if (firstFragment == null) {
                    firstFragment = ShiJiaCreatFragment.newInstance(proCode, mMaxNum);
                    ft.add(R.id.fragment_content_trade, firstFragment);
                } else {
                    ft.show(firstFragment);
                }
                break;
            case R.id.shijia_ping:
                if (secondFragment == null) {
                    secondFragment = ShiJiaPingFragment.newInstance(proCode, mMaxNum);
                    ft.add(R.id.fragment_content_trade, secondFragment);
                } else {
                    ft.show(secondFragment);
                }
                break;
            case R.id.zhijia_creat:
                if (thirdFragment == null) {
                    thirdFragment = ZhiJiaCreatFragment.newInstance(proCode, mMaxNum);
                    ft.add(R.id.fragment_content_trade, thirdFragment);
                } else {
                    ft.show(thirdFragment);
                }
                break;
            case R.id.zhijia_ping:
                if (fourFragment == null) {
                    fourFragment = ZhiJiaPingFragment.newInstance("", "");
                    ft.add(R.id.fragment_content_trade, fourFragment);
                } else {
                    ft.show(fourFragment);
                }
                break;
        }
        ft.commit();   //提交事务
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_name:
                getPopMore(view);
                break;
        }
    }

    private PopupWindow mPopProWindow;

    public void getPopMore(View view) {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.more_select_layout, null);
        /**
         * 如果pop是null就执行这个方法
         */
        if (mPopProWindow == null) {
            mPopProWindow = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            //        实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0xb0000000);
            //设置SelectPicPopupWindow弹出窗体的背景
            mPopProWindow.setBackgroundDrawable(dw);
            mPopProWindow.setOutsideTouchable(true);
            mPopProWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        }

        /**
         * 都会执行的方法
         */
        ListView mlistview = (ListView) contentView.findViewById(R.id.Listview_pop);
        GoodsTypeAdapter adapter = new GoodsTypeAdapter(getContext());
        adapter.setData(goodsTypeList);
        mlistview.setAdapter(adapter);

        //
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TitleInfo info = (TitleInfo) adapterView.getAdapter().getItem(i);
                mTitle.setText(info.getGoodsName());
                UserInfoAsyn userInfoAsyn = new UserInfoAsyn();
                userInfoAsyn.execute(sp.getString(SharePrenceUtil.OPEN_ID, ""), info.getGoodsCode());

                firstFragment = ShiJiaCreatFragment.newInstance(proCode, mMaxNum);
                getFragmentManager().beginTransaction().add(R.id.fragment_content_trade, firstFragment).commit();

//                MaxMinAsyn maxMinAsyn = new MaxMinAsyn();
                if (info.getGoodsCode().contains("A")){
//                    maxMinAsyn.execute("oil");
                    type = 1;
                    codeString = "oil";
                }else if (info.getGoodsCode().contains("B")){
//                    maxMinAsyn.execute("aug");
                    type = 2;
                    codeString = "aug";
                }else {
//                    maxMinAsyn.execute("copper");
                    type = 3;
                    codeString = "copper";
                }

                mPopProWindow.dismiss();
            }
        });

        //产生背景变暗效果
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.8f;
        getActivity().getWindow().setAttributes(lp);

        mPopProWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                //产生背景变暗效果
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        mPopProWindow.setFocusable(true);
        /**
         * 显示就消失
         */
        if (mPopProWindow.isShowing()) {
            mPopProWindow.dismiss();
        } else {
            mPopProWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
        }
    }


    /**
     * 首页商品种类
     */
    private class TitleInfoAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String message = HttpManagerUtil.getHttpManagerUtil().getHttpData(HttpManagerUtil.GOODS_TYPE);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s) && s.contains("status")) {
                Gson gson = new Gson();
                GoodTypeBean goodTypeBean = gson.fromJson(s, GoodTypeBean.class);
                parseGoodsType(goodTypeBean);
            } else {
                showToast(s);
            }

        }
    }

    private void parseGoodsType(GoodTypeBean goodTypeBean) {

        if (goodTypeBean.getStatus().equals("ok")) {
            List<GoodTypeBean.DataBean> data = goodTypeBean.getData();
            goodsTypeList = new ArrayList<>();
            int num = data.size();
            if (num > 0) {
                mTitle.setText(data.get(0).getGoods_name());

                UserInfoAsyn userInfoAsyn = new UserInfoAsyn();
                userInfoAsyn.execute(sp.getString(SharePrenceUtil.OPEN_ID, ""), data.get(0).getGoods_code());

                mMaxNum = data.get(0).getCcdnum();
                proCode = data.get(0).getGoods_code();
                firstFragment = ShiJiaCreatFragment.newInstance(proCode, mMaxNum);
                getFragmentManager().beginTransaction().add(R.id.fragment_content_trade, firstFragment).commit();

//                MaxMinAsyn maxMinAsyn = new MaxMinAsyn();
                if (proCode.contains("A")){
                    type = 1;
//                    maxMinAsyn.execute("oil");
                    codeString = "oil";
                }else if (proCode.contains("B")){
                    type = 2;
//                    maxMinAsyn.execute("aug");
                    codeString = "aug";
                }else {
                    type = 3;
//                    maxMinAsyn.execute("copper");
                    codeString = "copper";
                }

                for (int i = 0; i < num; i++) {
                    TitleInfo info = new TitleInfo();
                    info.setGoodsName(data.get(i).getGoods_name());
                    info.setGoodsCode(data.get(i).getGoods_code());
                    goodsTypeList.add(info);
                }
            }
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
                    .add("goods_code", strings[1])
                    .build();
            String message = HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody, HttpManagerUtil.GOODS_LIST);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                GoodsInfo info = gson.fromJson(s,GoodsInfo.class);
                parseGoodsInfo(info);
            }

        }
    }

    private void parseGoodsInfo(GoodsInfo info) {
        mUserName.setText("昵称："+info.getTruename());
        mYue.setText("￥"+info.getMoney());
    }

    /**
     * 最高价最低价下面数据
     */
    private class MaxAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String message = HttpManagerUtil.getHttpManagerUtil().getHttpData(HttpManagerUtil.BUY_OR_NO);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)){
                Gson gson = new Gson();
                BuyOrNoInfo info = gson.fromJson(s,BuyOrNoInfo.class);
                parseMaxInfo(info);
            }

        }
    }

    private void parseMaxInfo(BuyOrNoInfo info) {
//        mHigh.setText("最高："+info.getOil_price());
//        mLow.setText("最低："+info.getCu_price());
        double proPrice = 0;
        if (type == 1){
            mBuyNum.setText(info.getOil_price());
            mBuyNoNum.setText(info.getOil_price());
            proPrice = Double.parseDouble(info.getOil_price());
        }else if (type == 2){
            mBuyNum.setText(info.getAg_price());
            mBuyNoNum.setText(info.getAg_price());
            proPrice = Double.parseDouble(info.getAg_price());
        }else {
            mBuyNum.setText(info.getCu_price());
            mBuyNoNum.setText(info.getCu_price());
            proPrice = Double.parseDouble(info.getCu_price());
        }

        if (price>=proPrice){
            price = proPrice;
            mBuyLin.setBackgroundResource(R.drawable.rect_circle_red_shape);
            mBuyNoLin.setBackgroundResource(R.drawable.rect_circle_red_shape);
        }else {
            price = proPrice;
            mBuyLin.setBackgroundResource(R.drawable.rect_circle_yellow_shape);
            mBuyNoLin.setBackgroundResource(R.drawable.rect_circle_yellow_shape);
        }


    }

    private Boolean isNow = true;   //是否停止线程
    /**
     * 执行循环请求1s一次
     */
    private class ApplyHttpThread extends Thread{


        @Override
        public void run() {
            super.run();
            while (isNow){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MaxAsyn asyn = new MaxAsyn();
                asyn.execute();
            }
        }
    }
    /**
     * 执行循环请求3s一次
     */
    private class FuDongHttpThread extends Thread{


        @Override
        public void run() {
            super.run();
            while (isNow){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                FuDongAsyn fuDongAsyn = new FuDongAsyn();
                fuDongAsyn.execute(sp.getString(SharePrenceUtil.OPEN_ID, ""));

                MaxMinAsyn maxMinAsyn = new MaxMinAsyn();
                maxMinAsyn.execute(codeString);
            }
        }
    }



    /**
     * 买入卖出最高价最低价
     */
    private class MaxMinAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("cate", strings[0])
                    .build();
            String message = HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody, HttpManagerUtil.MAX_MIN);
            return message;
        }

            //{"min":"361.000","max":"361.000"}
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)&&s.contains("max")){
                try {
                    JSONObject object = new JSONObject(s);
//                    mBuyNum.setText(object.getString("max"));
//                    mBuyNoNum.setText(object.getString("min"));
                    mHigh.setText("最高："+object.getString("max"));
                    mLow.setText("最低："+object.getString("min"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                showToast(s);
            }

        }
    }
    /**
     * 浮动盈亏
     */
    private class FuDongAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("openid", strings[0])
                    .build();
            String message = HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody, HttpManagerUtil.FUDONG_INDEX);
            return message;
        }

            //{"min":"361.000","max":"361.000"}
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)&&s.contains("ins_yinkui")){
                    Gson gson = new Gson();
                FuDongBean fuDongBean = gson.fromJson(s,FuDongBean.class);
                parseFudongData(fuDongBean);
            }else {
                showToast(s);
            }

        }
    }

    private void parseFudongData(FuDongBean fuDongBean){
        mFuDong.setText(fuDongBean.getIns_yinkui());
    }
}
