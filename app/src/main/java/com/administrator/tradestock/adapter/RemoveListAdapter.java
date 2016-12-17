package com.administrator.tradestock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.model.RemoveBean;

import java.util.List;

/**
 * Created by 欢大哥 on 2016/12/7.
 */
public class RemoveListAdapter extends BaseAdapter {
    private Context context;
    private List<RemoveBean> list;
    public RemoveListAdapter(Context context){
        this.context = context;
    }

    public void setData(List<RemoveBean> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.remove_list_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.textNum = (TextView) view.findViewById(R.id.text_num);
            viewHolder.goodsName = (TextView) view.findViewById(R.id.goods_name);
            viewHolder.buyTxt = (TextView) view.findViewById(R.id.buy_or_no);
            viewHolder.goodsNum = (TextView) view.findViewById(R.id.goods_num);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        RemoveBean bean = (RemoveBean) getItem(i);
        viewHolder.textNum.setText(bean.getTextNum());
        viewHolder.goodsName.setText(bean.getGoodsName());
        viewHolder.goodsNum.setText(bean.getGoodsNum());
        if (bean.getIsBuy()!= null&&bean.getIsBuy().equals("1")){
            viewHolder.buyTxt.setText("卖出");
            viewHolder.buyTxt.setTextColor(context.getResources().getColor(R.color.green_color));
        }else {
            viewHolder.buyTxt.setText("买入");
            viewHolder.buyTxt.setTextColor(context.getResources().getColor(R.color.buy_color));
        }

        return view;
    }

    private class ViewHolder{
        TextView textNum,goodsName,buyTxt,goodsNum;

    }

}
