package com.administrator.tradestock.model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/17.
 */
public class GoodsInfo {


    /**
     * money : 10000.00
     * zhanyong_money : 0.00
     * ins_yinkui : 0.00
     * jinzhi : 10000.00
     * zx_jifen : 0
     * hy_jifen : 0
     * avatar : 100
     * k_line : data.mybiaojin.com
     * chongzhi : http://shipan.bsbce.com/chongzhi.php?mobile=18228753625
     * truename : hahha
     * title : 交易中心
     * mobile : 18228753625
     * fees_quan : 0
     * jyms : 短期
     * goods_list : [{"goods_code":"A1","goods_name":"燃料油1桶","status":"1","money":"5","fees":"0.500","profit_loss":"1","zhinajin":"0","cangxi":"0.150","jg_fees":"10","jg_bz":"100","jg_kd":"0","jg_gy":"100","nums":"1000000","nums_enable":"1","jiaoyi_time":"0-23-1-0-5-1-4-5-1-4-5-1-4-5-1-4-5-1-4-23-1","group":"1","max_zong_nums":"100","max_dan_nums":"50"}]
     */

    private String money;
    private String zhanyong_money;
    private String ins_yinkui;
    private String jinzhi;
    private String zx_jifen;
    private String hy_jifen;
    private String avatar;
    private String k_line;
    private String chongzhi;
    private String truename;
    private String title;
    private String mobile;
    private String fees_quan;
    private String jyms;
    /**
     * goods_code : A1
     * goods_name : 燃料油1桶
     * status : 1
     * money : 5
     * fees : 0.500
     * profit_loss : 1
     * zhinajin : 0
     * cangxi : 0.150
     * jg_fees : 10
     * jg_bz : 100
     * jg_kd : 0
     * jg_gy : 100
     * nums : 1000000
     * nums_enable : 1
     * jiaoyi_time : 0-23-1-0-5-1-4-5-1-4-5-1-4-5-1-4-5-1-4-23-1
     * group : 1
     * max_zong_nums : 100
     * max_dan_nums : 50
     */

    private List<GoodsListBean> goods_list;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getZhanyong_money() {
        return zhanyong_money;
    }

    public void setZhanyong_money(String zhanyong_money) {
        this.zhanyong_money = zhanyong_money;
    }

    public String getIns_yinkui() {
        return ins_yinkui;
    }

    public void setIns_yinkui(String ins_yinkui) {
        this.ins_yinkui = ins_yinkui;
    }

    public String getJinzhi() {
        return jinzhi;
    }

    public void setJinzhi(String jinzhi) {
        this.jinzhi = jinzhi;
    }

    public String getZx_jifen() {
        return zx_jifen;
    }

    public void setZx_jifen(String zx_jifen) {
        this.zx_jifen = zx_jifen;
    }

    public String getHy_jifen() {
        return hy_jifen;
    }

    public void setHy_jifen(String hy_jifen) {
        this.hy_jifen = hy_jifen;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getK_line() {
        return k_line;
    }

    public void setK_line(String k_line) {
        this.k_line = k_line;
    }

    public String getChongzhi() {
        return chongzhi;
    }

    public void setChongzhi(String chongzhi) {
        this.chongzhi = chongzhi;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFees_quan() {
        return fees_quan;
    }

    public void setFees_quan(String fees_quan) {
        this.fees_quan = fees_quan;
    }

    public String getJyms() {
        return jyms;
    }

    public void setJyms(String jyms) {
        this.jyms = jyms;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean {
        private String goods_code;
        private String goods_name;
        private String status;
        private String money;
        private String fees;
        private String profit_loss;
        private String zhinajin;
        private String cangxi;
        private String jg_fees;
        private String jg_bz;
        private String jg_kd;
        private String jg_gy;
        private String nums;
        private String nums_enable;
        private String jiaoyi_time;
        private String group;
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

        public String getJg_fees() {
            return jg_fees;
        }

        public void setJg_fees(String jg_fees) {
            this.jg_fees = jg_fees;
        }

        public String getJg_bz() {
            return jg_bz;
        }

        public void setJg_bz(String jg_bz) {
            this.jg_bz = jg_bz;
        }

        public String getJg_kd() {
            return jg_kd;
        }

        public void setJg_kd(String jg_kd) {
            this.jg_kd = jg_kd;
        }

        public String getJg_gy() {
            return jg_gy;
        }

        public void setJg_gy(String jg_gy) {
            this.jg_gy = jg_gy;
        }

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        public String getNums_enable() {
            return nums_enable;
        }

        public void setNums_enable(String nums_enable) {
            this.nums_enable = nums_enable;
        }

        public String getJiaoyi_time() {
            return jiaoyi_time;
        }

        public void setJiaoyi_time(String jiaoyi_time) {
            this.jiaoyi_time = jiaoyi_time;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
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
