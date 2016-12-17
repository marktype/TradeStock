package com.administrator.tradestock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.administrator.tradestock.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
public class LeftAdapter extends BaseAdapter {
    private Context context;
    private List<String> mListData;
    public LeftAdapter(Context context){
        this.context = context;
    }

    public void setData(List<String> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_left, null);
            holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String name = (String) getItem(position);

        holder.tvLeft.setText(name);

        return convertView;
    }

    class ViewHolder {
        TextView tvLeft;
    }
}