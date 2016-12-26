package com.administrator.tradestock.model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26.
 */
public class HoldBean {


    /**
     * serial_list : [{"user_money":"9.000","id":"1886699","serial":"2115314804964422102","goods_name":"燃料油1桶","cent_buy_point":"312.24","zhhy_truename":"宁夏招金大宗商品经营有限公司","buy_point":"312.03","mold_str":"green\">卖出","nums":"1","profit_loss":"1","opentime":"2016-11-30 17:00","loss_limit":"512.030","profit_limit":"162.030","goods_code":"A1","fees":"0.50","zszy_set_time":"17:00:50","money":"5.00","jyms":"0","inkui":"-0.21","mold":"1"},{"user_money":"9.000","id":"1886698","serial":"2115314804962936765","goods_name":"燃料油10桶","cent_buy_point":"312.24","zhhy_truename":"宁夏招金大宗商品经营有限公司","buy_point":"311.83","mold_str":"green\">卖出","nums":"1","profit_loss":"10","opentime":"2016-11-30 16:58","loss_limit":"327.830","profit_limit":"0.000","goods_code":"A2","fees":"5.00","zszy_set_time":"16:58:12","money":"200.00","jyms":"0","inkui":"-4.10","mold":"1"},{"user_money":"9.000","id":"1886697","serial":"2115314804962821942","goods_name":"燃料油1桶","cent_buy_point":"312.24","zhhy_truename":"宁夏招金大宗商品经营有限公司","buy_point":"311.97","mold_str":"green\">卖出","nums":"1","profit_loss":"1","opentime":"2016-11-30 16:58","loss_limit":"315.970","profit_limit":"0.000","goods_code":"A1","fees":"0.50","zszy_set_time":"16:58:02","money":"5.00","jyms":"0","inkui":"-0.27","mold":"1"},{"user_money":"9.000","id":"1718603","serial":"2115314782516933224","goods_name":"燃料油1桶","cent_buy_point":"312.24","zhhy_truename":"宁夏招金大宗商品经营有限公司","buy_point":"294.23","mold_str":"red\">买入","nums":"1","profit_loss":"1","opentime":"2016-11-04 17:28","loss_limit":"291.980","profit_limit":"296.230","goods_code":"A1","fees":"0.50","zszy_set_time":"18:06:08","money":"5.00","jyms":"0","inkui":"18.01","mold":"0"},{"user_money":"9.000","id":"1679041","serial":"2115314779610477671","goods_name":"再生铜10KG","cent_buy_point":"38.524","zhhy_truename":"宁夏招金大宗商品经营有限公司","buy_point":"31.971","mold_str":"red\">买入","nums":"1","profit_loss":"10","opentime":"2016-11-01 08:44","loss_limit":"31.571","profit_limit":"0.000","goods_code":"C1","fees":"0.00","zszy_set_time":"15:07:30","money":"5.00","jyms":"0","inkui":"65.53","mold":"0"},{"user_money":"9.000","id":"1679040","serial":"2115314779610416145","goods_name":"白银100G","cent_buy_point":"3.713","zhhy_truename":"宁夏招金大宗商品经营有限公司","buy_point":"3.904","mold_str":"red\">买入","nums":"1","profit_loss":"100","opentime":"2016-11-01 08:44","loss_limit":"0.000","profit_limit":"0.000","goods_code":"B1","fees":"0.00","zszy_set_time":"08:00:00","money":"5.00","jyms":"0","inkui":"0.00","mold":"0"},{"user_money":"9.000","id":"1679039","serial":"2115314779610333451","goods_name":"燃料油1桶","cent_buy_point":"312.24","zhhy_truename":"宁夏招金大宗商品经营有限公司","buy_point":"309.28","mold_str":"red\">买入","nums":"1","profit_loss":"1","opentime":"2016-11-01 08:43","loss_limit":"0.000","profit_limit":"0.000","goods_code":"A1","fees":"0.50","zszy_set_time":"08:00:00","money":"5.00","jyms":"0","inkui":"2.96","mold":"0"}]
     * jg_serial : null
     * user_array : {"money":"9844.40","zhanyong_money":"220.00","ins_yinkui":"81.92","jinzhi":"10146.32","zx_jifen":"0","hy_jifen":"0","avatar":"0","fees_quan":"0","k_line":"data.mybiaojin.com","chongzhi":"http://shipan.bsbce.com/chongzhi.php?mobile=13778009583","truename":"张杰","title":"交易中心","mobile":"13778009583","jyms":"短期","zhhy_truename":"宁夏招金大宗商品经营有限公司","zhhy_mobile":"18381684000"}
     */

    private Object jg_serial;
    /**
     * money : 9844.40
     * zhanyong_money : 220.00
     * ins_yinkui : 81.92
     * jinzhi : 10146.32
     * zx_jifen : 0
     * hy_jifen : 0
     * avatar : 0
     * fees_quan : 0
     * k_line : data.mybiaojin.com
     * chongzhi : http://shipan.bsbce.com/chongzhi.php?mobile=13778009583
     * truename : 张杰
     * title : 交易中心
     * mobile : 13778009583
     * jyms : 短期
     * zhhy_truename : 宁夏招金大宗商品经营有限公司
     * zhhy_mobile : 18381684000
     */

    private UserArrayBean user_array;
    /**
     * user_money : 9.000
     * id : 1886699
     * serial : 2115314804964422102
     * goods_name : 燃料油1桶
     * cent_buy_point : 312.24
     * zhhy_truename : 宁夏招金大宗商品经营有限公司
     * buy_point : 312.03
     * mold_str : green">卖出
     * nums : 1
     * profit_loss : 1
     * opentime : 2016-11-30 17:00
     * loss_limit : 512.030
     * profit_limit : 162.030
     * goods_code : A1
     * fees : 0.50
     * zszy_set_time : 17:00:50
     * money : 5.00
     * jyms : 0
     * inkui : -0.21
     * mold : 1
     */

    private List<SerialListBean> serial_list;

    public Object getJg_serial() {
        return jg_serial;
    }

    public void setJg_serial(Object jg_serial) {
        this.jg_serial = jg_serial;
    }

    public UserArrayBean getUser_array() {
        return user_array;
    }

    public void setUser_array(UserArrayBean user_array) {
        this.user_array = user_array;
    }

    public List<SerialListBean> getSerial_list() {
        return serial_list;
    }

    public void setSerial_list(List<SerialListBean> serial_list) {
        this.serial_list = serial_list;
    }

    public static class UserArrayBean {
        private String money;
        private String zhanyong_money;
        private String ins_yinkui;
        private String jinzhi;
        private String zx_jifen;
        private String hy_jifen;
        private String avatar;
        private String fees_quan;
        private String k_line;
        private String chongzhi;
        private String truename;
        private String title;
        private String mobile;
        private String jyms;
        private String zhhy_truename;
        private String zhhy_mobile;

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

        public String getFees_quan() {
            return fees_quan;
        }

        public void setFees_quan(String fees_quan) {
            this.fees_quan = fees_quan;
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

        public String getJyms() {
            return jyms;
        }

        public void setJyms(String jyms) {
            this.jyms = jyms;
        }

        public String getZhhy_truename() {
            return zhhy_truename;
        }

        public void setZhhy_truename(String zhhy_truename) {
            this.zhhy_truename = zhhy_truename;
        }

        public String getZhhy_mobile() {
            return zhhy_mobile;
        }

        public void setZhhy_mobile(String zhhy_mobile) {
            this.zhhy_mobile = zhhy_mobile;
        }
    }

    public static class SerialListBean {
        private String user_money;
        private String id;
        private String serial;
        private String goods_name;
        private String cent_buy_point;
        private String zhhy_truename;
        private String buy_point;
        private String mold_str;
        private String nums;
        private String profit_loss;
        private String opentime;
        private String loss_limit;
        private String profit_limit;
        private String goods_code;
        private String fees;
        private String zszy_set_time;
        private String money;
        private String jyms;
        private String inkui;
        private String mold;

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getCent_buy_point() {
            return cent_buy_point;
        }

        public void setCent_buy_point(String cent_buy_point) {
            this.cent_buy_point = cent_buy_point;
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

        public String getProfit_loss() {
            return profit_loss;
        }

        public void setProfit_loss(String profit_loss) {
            this.profit_loss = profit_loss;
        }

        public String getOpentime() {
            return opentime;
        }

        public void setOpentime(String opentime) {
            this.opentime = opentime;
        }

        public String getLoss_limit() {
            return loss_limit;
        }

        public void setLoss_limit(String loss_limit) {
            this.loss_limit = loss_limit;
        }

        public String getProfit_limit() {
            return profit_limit;
        }

        public void setProfit_limit(String profit_limit) {
            this.profit_limit = profit_limit;
        }

        public String getGoods_code() {
            return goods_code;
        }

        public void setGoods_code(String goods_code) {
            this.goods_code = goods_code;
        }

        public String getFees() {
            return fees;
        }

        public void setFees(String fees) {
            this.fees = fees;
        }

        public String getZszy_set_time() {
            return zszy_set_time;
        }

        public void setZszy_set_time(String zszy_set_time) {
            this.zszy_set_time = zszy_set_time;
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

        public String getInkui() {
            return inkui;
        }

        public void setInkui(String inkui) {
            this.inkui = inkui;
        }

        public String getMold() {
            return mold;
        }

        public void setMold(String mold) {
            this.mold = mold;
        }
    }
}
