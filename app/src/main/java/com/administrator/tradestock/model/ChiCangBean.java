package com.administrator.tradestock.model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/24.
 */
public class ChiCangBean {


    /**
     * serial : 10151614817030631698
     * goods_name : 燃料油1桶
     * zhhy_truename : 宁夏招金大宗商品经营有限公司
     * buy_point : 361.29
     * sale_point : 0.000
     * mold_str : red">买入
     * nums : 5
     * opentime : 1970-01-01 08:00
     * profit_loss : -20.00
     * profit_limit : 3600.00
     * loss_limit : 200.00
     * fees : 2.50
     * money : 25.00
     * jyms : 0
     * mold : 0
     */

    private List<SerialListBean> serial_list;

    public List<SerialListBean> getSerial_list() {
        return serial_list;
    }

    public void setSerial_list(List<SerialListBean> serial_list) {
        this.serial_list = serial_list;
    }

    public static class SerialListBean {
        private String serial;
        private String goods_name;
        private String zhhy_truename;
        private String buy_point;
        private String sale_point;
        private String mold_str;
        private String nums;
        private String opentime;
        private String profit_loss;
        private String profit_limit;
        private String loss_limit;
        private String fees;
        private String money;
        private String jyms;
        private String mold;

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getZhhy_truename() {
            return zhhy_truename;
        }

        public void setZhhy_truename(String zhhy_truename) {
            this.zhhy_truename = zhhy_truename;
        }

        public String getBuy_point() {
            return buy_point;
        }

        public void setBuy_point(String buy_point) {
            this.buy_point = buy_point;
        }

        public String getSale_point() {
            return sale_point;
        }

        public void setSale_point(String sale_point) {
            this.sale_point = sale_point;
        }

        public String getMold_str() {
            return mold_str;
        }

        public void setMold_str(String mold_str) {
            this.mold_str = mold_str;
        }

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        public String getOpentime() {
            return opentime;
        }

        public void setOpentime(String opentime) {
            this.opentime = opentime;
        }

        public String getProfit_loss() {
            return profit_loss;
        }

        public void setProfit_loss(String profit_loss) {
            this.profit_loss = profit_loss;
        }

        public String getProfit_limit() {
            return profit_limit;
        }

        public void setProfit_limit(String profit_limit) {
            this.profit_limit = profit_limit;
        }

        public String getLoss_limit() {
            return loss_limit;
        }

        public void setLoss_limit(String loss_limit) {
            this.loss_limit = loss_limit;
        }

        public String getFees() {
            return fees;
        }

        public void setFees(String fees) {
            this.fees = fees;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getJyms() {
            return jyms;
        }

        public void setJyms(String jyms) {
            this.jyms = jyms;
        }

        public String getMold() {
            return mold;
        }

        public void setMold(String mold) {
            this.mold = mold;
        }
    }
}
