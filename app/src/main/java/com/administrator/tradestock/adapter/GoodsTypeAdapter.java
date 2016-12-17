package com.administrator.tradestock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.model.TitleInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/12/17.
 */
public class GoodsTypeAdapter extends BaseAdapter {
    private List<TitleInfo> list;
    private Context context;
    public GoodsTypeAdapter(Context context){
        this.context = context;
    }

    public void setData(List<TitleInfo> list){
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
            view = LayoutInflater.from(context).inflate(R.layout.txt_item_list_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.item_txt);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        TitleInfo info = (TitleInfo) getItem(i);
        viewHolder.textView.setText(info.getGoodsName());

        return view;
    }

    private class ViewHolder{
        TextView textView;
    }
}
