package com.administrator.tradestock.adapter;

/**
 * Created by Administrator on 2016/12/7.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.model.RemoveBean;

import java.util.List;

public class DataAdapter extends BaseAdapter {
    private Context context;
    private List<RemoveBean> mListData;
    public DataAdapter(Context context){
        this.context = context;
    }

    public void setData(List<RemoveBean> list){
        this.mListData = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_data, null);
            holder.isBuy = (TextView) convertView.findViewById(R.id.is_buy);
            holder.num = (TextView) convertView.findViewById(R.id.goods_num);
            holder.leve = (TextView) convertView.findViewById(R.id.creat_levl);
            holder.leveTwo = (TextView) convertView.findViewById(R.id.cerat_levl_two);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RemoveBean bean = (RemoveBean) getItem(position);
        holder.leve.setText(bean.getTextNum());
        holder.leveTwo.setText(bean.getGoodsName());
        holder.num.setText(bean.getGoodsNum());
        if (bean.getIsBuy()!= null&&bean.getIsBuy().equals("1")){
            holder.isBuy.setText("卖出");
            holder.isBuy.setTextColor(context.getResources().getColor(R.color.green_color));
        }else {
            holder.isBuy.setText("买入");
            holder.isBuy.setTextColor(context.getResources().getColor(R.color.buy_color));
        }

        return convertView;
    }

    class ViewHolder {
        TextView isBuy,num,leve,leveTwo;
    }
}