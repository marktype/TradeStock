package com.administrator.tradestock.fragment;


import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.adapter.DataAdapter;
import com.administrator.tradestock.adapter.LeftAdapter;
import com.administrator.tradestock.customview.NoscrollListView;
import com.administrator.tradestock.customview.SyncHorizontalScrollView;
import com.administrator.tradestock.model.HoldBean;
import com.administrator.tradestock.model.RemoveBean;
import com.administrator.tradestock.util.HttpManagerUtil;
import com.administrator.tradestock.util.SharePrenceUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoldFragment extends BaseFragment implements View.OnClickListener{
    private NoscrollListView mLeft;
    private LeftAdapter mLeftAdapter;

    private NoscrollListView mData;
    private DataAdapter mDataAdapter;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private ScrollView mScrollview;
    private View mView;
    private TextView mMoreTxt;

    private ApplyHttpThread thread;
    private SharedPreferences sp;
    private TextView mNoData;

    public HoldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null){
            sp = SharePrenceUtil.getShareSaveUserInfo(getContext());
            mView = inflater.inflate(R.layout.fragment_hold_one_layout, container, false);
            initView();
        }
        return mView;
    }


    private void initView(){
        mLeft = (NoscrollListView) mView.findViewById(R.id.lv_left);
        mData = (NoscrollListView) mView.findViewById(R.id.lv_data);
        mDataHorizontal = (SyncHorizontalScrollView) mView.findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) mView.findViewById(R.id.header_horizontal);
        mScrollview = (ScrollView) mView.findViewById(R.id.scroll_content);
        mMoreTxt = (TextView) mView.findViewById(R.id.more_select);
        mNoData = (TextView) mView.findViewById(R.id.txt_no_data);

        mDataHorizontal.setScrollView(mHeaderHorizontal);
        mHeaderHorizontal.setScrollView(mDataHorizontal);

        mLeftAdapter= new LeftAdapter(getContext());

        mDataAdapter = new DataAdapter(getContext());


        mMoreTxt.setOnClickListener(this);
    }


    private List<String> getNameData(){
        List<String> list = new ArrayList<>();
        for (int i = 0;i<30;i++){
            list.add("巴蜀银AWW(10kg)");
        }
        return list;
    }

    private List<RemoveBean> getListData(){
        List<RemoveBean> list = new ArrayList<>();
        for (int i = 0;i<30;i++){
            RemoveBean bean = new RemoveBean();
            bean.setGoodsName("123.00");
            bean.setTextNum("12456");
            if (i%2 == 0){
                bean.setIsBuy("1");
            }else {
                bean.setIsBuy("2");
            }
            bean.setGoodsNum("23");
            list.add(bean);
        }
        return list;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.more_select:
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
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.txt_item_list_layout, getArrayData());
        mlistview.setAdapter(adapter);

        //
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = (String) adapterView.getAdapter().getItem(i);
                mMoreTxt.setText(str);
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

    private ArrayList<String> getArrayData(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0;i<15;i++){
            list.add("巴蜀银="+i);
        }
        return list;
    }


    /**
     * 持仓列表信息
     */
    private class HoldCangAsyn extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("openid", strings[0])
                    .build();
            String message = HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody, HttpManagerUtil.HOLD_SERIAL);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)&&s.contains("serial_list")){
                Gson gson = new Gson();
                HoldBean info = gson.fromJson(s,HoldBean.class);
                parseGoodsInfo(info);
            }else {
                showToast(s);
            }
        }
    }

    private void parseGoodsInfo(HoldBean info) {
        List<HoldBean.SerialListBean> serialListBeen = info.getSerial_list();
        if (serialListBeen != null){
            mNoData.setVisibility(View.GONE);
            mScrollview.setVisibility(View.VISIBLE);
            ArrayList<String> nameList = new ArrayList<>();
            List<RemoveBean> list = new ArrayList<>();
            for (int i = 0;i<serialListBeen.size();i++){
                HoldBean.SerialListBean serialListBean = serialListBeen.get(i);
                nameList.add(serialListBean.getGoods_name());

                RemoveBean bean = new RemoveBean();
                bean.setGoodsName(serialListBean.getGoods_name());
                bean.setGoodsPrice(serialListBean.getCent_buy_point());
                if (i%2 == 0){
                    bean.setIsBuy("1");
                }else {
                    bean.setIsBuy("2");
                }
                bean.setGoodsNum(serialListBean.getNums());
                list.add(bean);
            }

            mLeftAdapter.setData(nameList);
            mLeft.setAdapter(mLeftAdapter);
            mDataAdapter.setData(list);
            mData.setAdapter(mDataAdapter);

        }else {
            mNoData.setVisibility(View.VISIBLE);
            mScrollview.setVisibility(View.GONE);
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        isNow = true;
        if (thread == null){
            thread = new ApplyHttpThread();
            thread.start();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        isNow = false;
        thread = null;
    }

    private Boolean isNow = true;   //是否停止线程
    /**
     * 执行循环请求
     */
    private class ApplyHttpThread extends Thread{


        @Override
        public void run() {
            super.run();
            while (isNow){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                HoldCangAsyn asyn = new HoldCangAsyn();
                asyn.execute(sp.getString(SharePrenceUtil.OPEN_ID,""));
            }
        }
    }
}
