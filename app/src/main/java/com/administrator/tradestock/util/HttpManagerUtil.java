package com.administrator.tradestock.util;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/15.
 */
public class HttpManagerUtil {
    private static HttpManagerUtil httpManagerUtil;

    public static String SERVICE_URL = "http://moni.mybiaojin.com/jy.php";   //服务器地址

    public static String BUY_OR_NO = "http://moni.mybiaojin.com/getapi.php";  //首页买入卖出下面的值
    public static String INFO_CHECK = SERVICE_URL+"?jy=sever_rand";    //短信验证
    public static String LOGIN = SERVICE_URL+"?jy=app_login";    //登陆
    public static String REGISTER = SERVICE_URL+"?jy=user_reg";    //注册
    public static String RESET_PASSWORD = SERVICE_URL+"?jy=re_password";    //重置密码
    public static String GOODS_LIST = SERVICE_URL+"?jy=wx_load";    //产品列表
    public static String ADD_SERIAL = SERVICE_URL+"?jy=add_serial";    //建仓
    public static String STOP_LOST = SERVICE_URL+"?jy=sz_zszy";    //止损
    public static String SHIJIAPINGCANG = SERVICE_URL+"?jy=sjpc";    //市价平仓
    public static String ZHIJIAJIANCANG = SERVICE_URL+"?jy=zjjc";    //指价建仓
    public static String LOAD_PING_LIST = SERVICE_URL+"?jy=load_pcd";    //平仓单列表
    public static String SELL_SERIAL = SERVICE_URL+"?jy=sell_serial";    //平仓
    public static String HOLD_SERIAL = SERVICE_URL+"?jy=serial";    //持仓+余额
    public static String ADDRESS = SERVICE_URL+"?jy=addr";    //收货地址
    public static String FUDONG_INDEX = SERVICE_URL+"?jy=index30";    //浮动盈亏
    public static String USER_INFO = SERVICE_URL+"?jy=user";    //个人信息
    public static String MY_CARD_LIST = SERVICE_URL+"?jy=mycard_list";    //我的银行卡列表
    public static String ADD_CARD = SERVICE_URL+"?jy=add_card";    //添加银行卡
    public static String JIEBANG_CARD = SERVICE_URL+"?jy=jiebang";    //解绑银行卡
    public static String REAL_NAME_CHECK = SERVICE_URL+"?jy=smrz";    //实名认证
    public static String READ_REAL_NAME_STATUS = SERVICE_URL+"?jy=read_smrz";    //读取实名认证状态
    public static String GET_MONEY_LIST = SERVICE_URL+"?jy=cztx_log";    //提现记录
    public static String PUBLIC_INFO = SERVICE_URL+"?jy=gonggao";    //公告
    public static String FUNDS_LOG = SERVICE_URL+"?jy=funds_log";    //账户流水明细
    public static String GOODS_TYPE = SERVICE_URL+"?jy=goodstype";    //商品种类
    public static String MAX_MIN = SERVICE_URL+"?jy=max_min";    //首页最大值最小值，A(oil)/B(aug)/C(copper)

    public static HttpManagerUtil getHttpManagerUtil(){
        if (httpManagerUtil == null){
            httpManagerUtil = new HttpManagerUtil();
        }
        return httpManagerUtil;
    }

    private HttpManagerUtil(){

    }


    public String postHttpData(RequestBody formBody, String url) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getHttpData(String url) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
