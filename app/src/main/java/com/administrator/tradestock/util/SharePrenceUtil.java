package com.administrator.tradestock.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 欢大哥 on 2016/5/24.
 */
public class SharePrenceUtil {
    private static SharedPreferences user;

    /**
     * 保存用户token
     * @param context
     */
    public static final String USER_INFO = "com.deepblue.shop.user";
    public static final String TOKEN = "token";      //用户token
    public static final String NAME = "name";      //用户名字
    public static final String YU_E = "yue";      //用户余额
    public static final String USER_PHONE = "phone";    //电话
    public static final String USER_PASSWORD = "password";    //密码
    public static final String USER_POINTS = "points";    //积分
    public static final String USER_ID = "uid";       //用户id
    public static final String USER_IMAGE = "headimg";   //图片
    public static final String OPEN_ID = "openid";   //

    public static SharedPreferences getShareSaveUserInfo(Context context){
        if (user == null){
            user = context.getSharedPreferences(USER_INFO,Context.MODE_PRIVATE);
        }
        return user;
    }


}
