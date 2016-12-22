package com.administrator.tradestock.util;

/**
 * Created by Administrator on 2016/12/22.
 */
public class FuntionUtil {

    /**
     * 银行卡号前面数字用*代替且4位空一格
     * @param strNum
     * @return
     */
    public static String getStarReplaceBankCard(String strNum){
        strNum = strNum.replaceAll("\\d(?=[\\d]{4})", "*");   //除了倒数4位，前面用*代替
        String blank = strNum.replaceAll(".{4}(?!$)", "$0 ");  //4位空一格
        return blank;
    }
}
