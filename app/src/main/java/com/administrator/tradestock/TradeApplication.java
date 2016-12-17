package com.administrator.tradestock;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/17.
 */
public class TradeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private ArrayList<Activity> activity = new ArrayList();
    private static TradeApplication instance;           //使用懒汉式单例

    public void addActivity(Activity act){
        activity.add(act);
    }
    public static TradeApplication getInstance() {      //单例实例化
        if (instance ==null) {
            instance = new TradeApplication();
        }
        return instance;
    }

//遍历集合全部结束

    public void finishAll(){
        for (Activity object : activity) {
            if (!object.isFinishing()) {
                object.finish();
            }
        }
    }
}

