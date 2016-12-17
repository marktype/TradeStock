package com.administrator.tradestock.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/15.
 */
public class UserRegisterInfo implements Parcelable {

    private String username;
    private String password;
    private String phone;
    private String sms;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.phone);
        dest.writeString(this.sms);
    }

    public UserRegisterInfo() {
    }

    protected UserRegisterInfo(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.phone = in.readString();
        this.sms = in.readString();
    }

    public static final Parcelable.Creator<UserRegisterInfo> CREATOR = new Parcelable.Creator<UserRegisterInfo>() {
        @Override
        public UserRegisterInfo createFromParcel(Parcel source) {
            return new UserRegisterInfo(source);
        }

        @Override
        public UserRegisterInfo[] newArray(int size) {
            return new UserRegisterInfo[size];
        }
    };
}
