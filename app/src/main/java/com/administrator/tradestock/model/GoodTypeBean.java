package com.administrator.tradestock.model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/17.
 */
public class GoodTypeBean {


    /**
     * status : ok
     * data : [{"goods_code":"A1","goods_name":"燃料油1桶","status":"1","money":"5.000","fees":"0.500","profit_loss":"1.000","zhinajin":"0","cangxi":"0.150","ccdnum":0,"max_zong_nums":"100","max_dan_nums":"50"},{"goods_code":"A2","goods_name":"燃料油10桶","status":"1","money":"200.000","fees":"5.000","profit_loss":"10.000","zhinajin":"0","cangxi":"1.500","ccdnum":0,"max_zong_nums":"500","max_dan_nums":"50"},{"goods_code":"A3","goods_name":"燃料油50桶","status":"1","money":"500.000","fees":"25.000","profit_loss":"50.000","zhinajin":"0","cangxi":"7.500","ccdnum":0,"max_zong_nums":"250","max_dan_nums":"50"},{"goods_code":"A4","goods_name":"燃料油500桶","status":"1","money":"5000.000","fees":"250.000","profit_loss":"500.000","zhinajin":"0","cangxi":"150.000","ccdnum":0,"max_zong_nums":"100","max_dan_nums":"50"},{"goods_code":"B1","goods_name":"白银100G","status":"1","money":"5.000","fees":"0.500","profit_loss":"100.000","zhinajin":"0","cangxi":"0.150","ccdnum":0,"max_zong_nums":"100","max_dan_nums":"50"},{"goods_code":"B2","goods_name":"白银500G","status":"1","money":"100.000","fees":"2.500","profit_loss":"500.000","zhinajin":"0","cangxi":"0.750","ccdnum":0,"max_zong_nums":"500","max_dan_nums":"50"},{"goods_code":"B3","goods_name":"白银5000G","status":"1","money":"1000.000","fees":"25.000","profit_loss":"5000.000","zhinajin":"0","cangxi":"7.500","ccdnum":0,"max_zong_nums":"200","max_dan_nums":"50"},{"goods_code":"B4","goods_name":"白银15KG","status":"1","money":"3000.000","fees":"75.000","profit_loss":"15000.000","zhinajin":"0","cangxi":"22.500","ccdnum":0,"max_zong_nums":"100","max_dan_nums":"50"},{"goods_code":"C1","goods_name":"再生铜10KG","status":"1","money":"5.000","fees":"0.500","profit_loss":"10.000","zhinajin":"0","cangxi":"0.150","ccdnum":0,"max_zong_nums":"100","max_dan_nums":"50"},{"goods_code":"C2","goods_name":"再生铜50KG","status":"1","money":"100.000","fees":"2.000","profit_loss":"50.000","zhinajin":"0","cangxi":"0.750","ccdnum":0,"max_zong_nums":"500","max_dan_nums":"50"},{"goods_code":"C3","goods_name":"再生铜200KG","status":"1","money":"200.000","fees":"10.000","profit_loss":"200.000","zhinajin":"0","cangxi":"3.000","ccdnum":0,"max_zong_nums":"500","max_dan_nums":"50"},{"goods_code":"C4","goods_name":"再生铜1T","status":"1","money":"1000.000","fees":"50.000","profit_loss":"1000.000","zhinajin":"0","cangxi":"15.000","ccdnum":0,"max_zong_nums":"100","max_dan_nums":"50"}]
     */

    private String status;
    /**
     * goods_code : A1
     * goods_name : 燃料油1桶
     * status : 1
     * money : 5.000
     * fees : 0.500
     * profit_loss : 1.000
     * zhinajin : 0
     * cangxi : 0.150
     * ccdnum : 0
     * max_zong_nums : 100
     * max_dan_nums : 50
     */

    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String goods_code;
        private String goods_name;
        private String status;
        private String money;
        private String fees;
        private String profit_loss;
        private String zhinajin;
        private String cangxi;
        private int ccdnum;
        private String max_zong_nums;
        private String max_dan_nums;

        public String getGoods_code() {
            return goods_code;
        }

        public void setGoods_code(String goods_code) {
            this.goods_code = goods_code;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getFees() {
            return fees;
        }

        public void setFees(String fees) {
            this.fees = fees;
        }

        public String getProfit_loss() {
            return profit_loss;
        }

        public void setProfit_loss(String profit_loss) {
            this.profit_loss = profit_loss;
        }

        public String getZhinajin() {
            return zhinajin;
        }

        public void setZhinajin(String zhinajin) {
            this.zhinajin = zhinajin;
        }

        public String getCangxi() {
            return cangxi;
        }

        public void setCangxi(String cangxi) {
            this.cangxi = cangxi;
        }

        public int getCcdnum() {
            return ccdnum;
        }

        public void setCcdnum(int ccdnum) {
            this.ccdnum = ccdnum;
        }

        public String getMax_zong_nums() {
            return max_zong_nums;
        }

        public void setMax_zong_nums(String max_zong_nums) {
            this.max_zong_nums = max_zong_nums;
        }

        public String getMax_dan_nums() {
            return max_dan_nums;
        }

        public void setMax_dan_nums(String max_dan_nums) {
            this.max_dan_nums = max_dan_nums;
        }
    }
}
