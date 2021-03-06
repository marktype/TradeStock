package com.administrator.tradestock.fragment;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.model.BuyInfo;
import com.administrator.tradestock.util.HttpManagerUtil;
import com.administrator.tradestock.util.SharePrenceUtil;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ZhiJiaCreatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZhiJiaCreatFragment extends BaseFragment implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private BuyInfo mBuyInfo;
    private String mBuyType;
    private SharedPreferences sp;
    private View mView;
    private EditText mEditStopLost,mEditStopGet,mHandNum,mPriceNum;


    public ZhiJiaCreatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ZhiJiaCreatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ZhiJiaCreatFragment newInstance(BuyInfo param1) {
        ZhiJiaCreatFragment fragment = new ZhiJiaCreatFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBuyInfo = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null){
            sp = SharePrenceUtil.getShareSaveUserInfo(getContext());
            mView = inflater.inflate(R.layout.fragment_zhi_jia_creat, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        CheckBox mCheckStopLost = (CheckBox) mView.findViewById(R.id.check_stop_lost);
        CheckBox mCheckStopGet = (CheckBox) mView.findViewById(R.id.check_stop_get);
        mEditStopLost = (EditText) mView.findViewById(R.id.edit_stop_lost);
        mEditStopGet = (EditText) mView.findViewById(R.id.edit_stop_get);
        TextView mClearData = (TextView) mView.findViewById(R.id.clear_data);
        TextView mPlaceOrder = (TextView) mView.findViewById(R.id.place_order);
        TextView mOncePlaceOrder = (TextView) mView.findViewById(R.id.once_place_order);
        mHandNum = (EditText) mView.findViewById(R.id.hand_num);
        TextView mMaxNum = (TextView) mView.findViewById(R.id.max_num);
        mPriceNum = (EditText) mView.findViewById(R.id.price_num);
        RadioGroup mGroup = (RadioGroup) mView.findViewById(R.id.into_group);


        mEditStopLost.setEnabled(false);
        mEditStopLost.setText("");
        mEditStopGet.setEnabled(false);
        mEditStopGet.setText("");
        mCheckStopLost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mEditStopLost.setEnabled(true);
                }else {
                    mEditStopLost.setEnabled(false);
                    mEditStopLost.setText("");
                }
            }
        });
        mCheckStopGet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mEditStopGet.setEnabled(true);
                }else {
                    mEditStopGet.setEnabled(false);
                    mEditStopGet.setText("");
                }
            }
        });
        mClearData.setOnClickListener(this);
        mPlaceOrder.setOnClickListener(this);
        mOncePlaceOrder.setOnClickListener(this);
        mMaxNum.setOnClickListener(this);
        mGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.clear_data:
                mHandNum.setText("");
                mEditStopLost.setText("");
                mEditStopGet.setText("");
                mPriceNum.setText("");
                break;
            case R.id.place_order:
                String num = mHandNum.getText().toString().trim();
                String zhiLost = mEditStopLost.getText().toString().trim();
                String ZhiGet = mEditStopGet.getText().toString().trim();
                if (!TextUtils.isEmpty(num)){
                    int iNum= Integer.parseInt(num);
                    if (iNum>mBuyInfo.getMaxNum()){
                        showToast("输入手数超过最大值");
                    }else if (TextUtils.isEmpty(mBuyType)){
                        showToast("请选择买入或卖出");
                    }else {
                        ZhijiaCreatAsyn shiJiaCreatAsyn = new ZhijiaCreatAsyn();
                        shiJiaCreatAsyn.execute(num,mBuyInfo.getProCode(),mBuyType,sp.getString(SharePrenceUtil.OPEN_ID,""),ZhiGet,zhiLost);
                    }
                }else {
                    showToast("手数为空，不能提交");
                }
                break;
            case R.id.once_place_order:
                ZhijiaCreatAsyn shiJiaCreatAsyn = new ZhijiaCreatAsyn();
                shiJiaCreatAsyn.execute("10",mBuyInfo.getProCode(),"0",sp.getString(SharePrenceUtil.OPEN_ID,""),"","");
                break;
            case R.id.max_num:
                mHandNum.setText(mBuyInfo.getMoney()+"");
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.buy_into:
                mBuyType = "0";
                break;
            case R.id.buy_no:
                mBuyType = "1";
                break;
        }
    }

    /**
     *指价建仓
     */
    private class ZhijiaCreatAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("prod_nums", strings[0])
                    .add("prod_code", strings[1])
                    .add("prod_mold", strings[2])
                    .add("prod_type", "1")
                    .add("openid", strings[3])
                    .add("profit_limit", strings[4])
                    .add("loss_limit", strings[5])
                    .build();
            String message =  HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody,HttpManagerUtil.ZHIJIAJIANCANG);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.contains("status")&&s.contains("ok")){
                showToast("下单成功");
            }else {
                showToast(s);
            }
        }
    }
}
