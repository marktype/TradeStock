package com.administrator.tradestock.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.adapter.DataAdapter;
import com.administrator.tradestock.adapter.LeftAdapter;
import com.administrator.tradestock.customview.NoscrollListView;
import com.administrator.tradestock.customview.SyncHorizontalScrollView;
import com.administrator.tradestock.model.RemoveBean;

import java.util.ArrayList;
import java.util.List;

public class TradeListActivity extends BascActivity implements View.OnClickListener{
    private NoscrollListView mLeft;
    private LeftAdapter mLeftAdapter;

    private NoscrollListView mData;
    private DataAdapter mDataAdapter;

    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;

    private TextView mMoreTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.app_yellow));
        setContentView(R.layout.activity_trade_list);
        initView();
    }

    private void initView(){
        mLeft = (NoscrollListView) findViewById(R.id.lv_left);
        mData = (NoscrollListView) findViewById(R.id.lv_data);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);
        mMoreTxt = (TextView) findViewById(R.id.more_select);

        mDataHorizontal.setScrollView(mHeaderHorizontal);
        mHeaderHorizontal.setScrollView(mDataHorizontal);

        mLeftAdapter= new LeftAdapter(this);
        mLeftAdapter.setData(getNameData());
        mLeft.setAdapter(mLeftAdapter);

        mDataAdapter = new DataAdapter(this);
        mDataAdapter.setData(getListData());
        mData.setAdapter(mDataAdapter);

        ImageView mBackImg= (ImageView) findViewById(R.id.back_img);

        mBackImg.setOnClickListener(this);
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

    private PopupWindow mPopProWindow;
    public void getPopMore(View view) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.more_select_layout, null);
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
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.txt_item_list_layout, getArrayData());
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
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.8f;
        getWindow().setAttributes(lp);

        mPopProWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                //产生背景变暗效果
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.more_select:
                getPopMore(view);
                break;
            case R.id.back_img:
                finish();
                break;
        }
    }
}
