package com.administrator.tradestock.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.fragment.HoldFragment;
import com.administrator.tradestock.fragment.MyFragment;
import com.administrator.tradestock.fragment.RemoveListFragment;
import com.administrator.tradestock.fragment.TradeFragment;

public class MainActivity extends BascActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.app_yellow));
        setContentView(R.layout.activity_main);
        initTab();
    }


    //自定义tab
    public View setTabMenu(String name, int image) {
        View v = LayoutInflater.from(this).inflate(R.layout.tab_own_item_layout, null);
        TextView menuText = (TextView) v.findViewById(R.id.tab_menu_txt);
        ImageView menuImg = (ImageView) v.findViewById(R.id.tab_image);
        menuText.setText(name);
        menuImg.setImageResource(image);
        return v;
    }

    public void initTab(){
        FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //使用fragment代替activity转换实现
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(setTabMenu("交易", R.drawable.tab_item1_selector)), TradeFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(setTabMenu("持仓", R.drawable.tab_item2_selector)), HoldFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab4").setIndicator(setTabMenu("撤单", R.drawable.tab_item3_selector)), RemoveListFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab5").setIndicator(setTabMenu("我的", R.drawable.tab_item4_selector)), MyFragment.class, null);

        tabHost.getTabWidget().setDividerDrawable(null);     //去除tab之间的分割线



    }

}
