package com.administrator.tradestock.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.adapter.RemoveListAdapter;
import com.administrator.tradestock.model.RemoveBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemoveListFragment extends BaseFragment {
    private View mView;

    public RemoveListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null){
            mView= inflater.inflate(R.layout.fragment_remove_list_layout, container, false);
            initWidget();
        }
        return mView;
    }


    private void initWidget(){
        ListView mList = (ListView) mView.findViewById(R.id.remove_list);
        RemoveListAdapter adapter = new RemoveListAdapter(getContext());
        adapter.setData(getData());
        mList.setAdapter(adapter);
    }

    private List<RemoveBean> getData(){
        List<RemoveBean> list = new ArrayList<>();
        for (int i = 0;i<10;i++){
            RemoveBean bean = new RemoveBean();
            bean.setGoodsName("巴蜀银AWW(10kg)");
            bean.setTextNum("1245644215645");
            if (i%2 == 0){
                bean.setIsBuy("1");
            }else {
                bean.setIsBuy("2");
            }
            bean.setGoodsNum("123");
            list.add(bean);
        }
        return list;
    }

}
