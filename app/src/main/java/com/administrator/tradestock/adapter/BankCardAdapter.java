package com.administrator.tradestock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.model.BankInfoBean;
import com.administrator.tradestock.util.FuntionUtil;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/12/21.
 */
public class BankCardAdapter extends BaseAdapter {
    private Context context;
    private List<BankInfoBean> list;
    public BankCardAdapter(Context context){
        this.context = context;
    }

    public void setData(List<BankInfoBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.bank_card_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.bankName = (TextView) view.findViewById(R.id.bank_card_title);
            viewHolder.bankNum = (TextView) view.findViewById(R.id.bank_card_num);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        BankInfoBean infoBean = (BankInfoBean) getItem(i);
        viewHolder.bankName.setText(infoBean.getBankName());
        viewHolder.bankNum.setText(FuntionUtil.getStarReplaceBankCard(infoBean.getBankNum()));

        return view;
    }
    private class ViewHolder{
        TextView bankName;
        TextView bankNum;
    }

}
