package com.administrator.tradestock.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/1/4.
 */
public class BuyInfo implements Parcelable {

    private String proCode;
    private int maxNum;
    private String money;

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.proCode);
        dest.writeInt(this.maxNum);
        dest.writeString(this.money);
    }

    public BuyInfo() {
    }

    protected BuyInfo(Parcel in) {
        this.proCode = in.readString();
        this.maxNum = in.readInt();
        this.money = in.readString();
    }

    public static final Parcelable.Creator<BuyInfo> CREATOR = new Parcelable.Creator<BuyInfo>() {
        @Override
        public BuyInfo createFromParcel(Parcel source) {
            return new BuyInfo(source);
        }

        @Override
        public BuyInfo[] newArray(int size) {
            return new BuyInfo[size];
        }
    };
}
