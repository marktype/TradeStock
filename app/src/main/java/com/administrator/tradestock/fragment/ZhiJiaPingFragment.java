package com.administrator.tradestock.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.tradestock.R;
import com.administrator.tradestock.adapter.RemoveListAdapter;
import com.administrator.tradestock.customview.NoscrollListView;
import com.administrator.tradestock.model.RemoveBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ZhiJiaPingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZhiJiaPingFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;

    public ZhiJiaPingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ZhiJiaPingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ZhiJiaPingFragment newInstance(String param1, String param2) {
        ZhiJiaPingFragment fragment = new ZhiJiaPingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_zhi_jia_ping, container, false);
            initWidget();
        }
        return mView;
    }

    private void initWidget(){
        NoscrollListView mList = (NoscrollListView) mView.findViewById(R.id.remove_list);
        RemoveListAdapter adapter = new RemoveListAdapter(getContext());
        adapter.setData(getData());
        mList.setAdapter(adapter);
    }

    private List<RemoveBean> getData(){
        List<RemoveBean> list = new ArrayList<>();
        for (int i = 0;i<20;i++){
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
