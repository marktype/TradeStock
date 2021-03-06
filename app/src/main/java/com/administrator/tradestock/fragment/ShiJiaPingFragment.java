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
 * Use the {@link ShiJiaPingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShiJiaPingFragment extends BaseFragment implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private BuyInfo mBuyInfo;
    private View mView;
    private String mBuyType;
    private SharedPreferences sp;
    private EditText mEditPoint,mEditBack,mHandNum;


    public ShiJiaPingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ShiJiaPingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShiJiaPingFragment newInstance(BuyInfo param1) {
        ShiJiaPingFragment fragment = new ShiJiaPingFragment();
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
            mView = inflater.inflate(R.layout.fragment_shi_jia_ping, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        CheckBox mCheckPoint = (CheckBox) mView.findViewById(R.id.check_point);
        CheckBox mCheckBack = (CheckBox) mView.findViewById(R.id.check_back);
        mEditPoint = (EditText) mView.findViewById(R.id.edit_point);
        mEditBack = (EditText) mView.findViewById(R.id.edit_back);
        TextView mClearData = (TextView) mView.findViewById(R.id.clear_data);
        TextView mPlaceOrder = (TextView) mView.findViewById(R.id.place_order);
        TextView mOncePlaceOrder = (TextView) mView.findViewById(R.id.once_place_order);
        mHandNum = (EditText) mView.findViewById(R.id.hand_num);
        TextView mMaxNum = (TextView) mView.findViewById(R.id.max_num);
        RadioGroup mGroup = (RadioGroup) mView.findViewById(R.id.into_group);
        TextView mPrice = (TextView) mView.findViewById(R.id.price_creat);

        mPrice.setText("￥"+mBuyInfo.getMoney());

        mEditPoint.setEnabled(false);
        mEditPoint.setText("");
        mEditBack.setEnabled(false);
        mEditBack.setText("");
        mCheckPoint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mEditPoint.setEnabled(true);
                }else {
                    mEditPoint.setEnabled(false);
                    mEditPoint.setText("");
                }
            }
        });
        mCheckBack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mEditBack.setEnabled(true);
                }else {
                    mEditBack.setEnabled(false);
                    mEditBack.setText("");
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
                mEditPoint.setText("");
                mEditBack.setText("");
                break;
            case R.id.place_order:
                String num = mHandNum.getText().toString().trim();
                if (!TextUtils.isEmpty(num)){
                    int iNum= Integer.parseInt(num);
                    if (iNum>mBuyInfo.getMaxNum()){
                        showToast("输入手数超过最大值");
                    }else if (TextUtils.isEmpty(mBuyType)){
                        showToast("请选择买入或卖出");
                    }else {
                        ShiJiaCreatAsyn shiJiaCreatAsyn = new ShiJiaCreatAsyn();
                        shiJiaCreatAsyn.execute(num,mBuyInfo.getProCode(),mBuyType,sp.getString(SharePrenceUtil.OPEN_ID,""));
                    }
                }else {
                    showToast("手数为空，不能提交");
                }
                break;
            case R.id.once_place_order:
                ShiJiaCreatAsyn shiJiaCreatAsyn = new ShiJiaCreatAsyn();
                shiJiaCreatAsyn.execute("10",mBuyInfo.getProCode(),"0",sp.getString(SharePrenceUtil.OPEN_ID,""));
                break;
            case R.id.max_num:
                mHandNum.setText(mBuyInfo.getMaxNum()+"");
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
     *市价平仓
     */
    private class ShiJiaCreatAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("nums", strings[0])
                    .add("goods_code", strings[1])
                    .add("mold", strings[2])
                    .add("openid", strings[3])
                    .build();
            String message =  HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody,HttpManagerUtil.SHIJIAPINGCANG);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.contains("pock")){
                showToast("下单成功");
            }else {
                showToast(s);
            }
        }
    }
}
