package com.administrator.tradestock.fragment;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.adapter.RemoveListAdapter;
import com.administrator.tradestock.customview.NoscrollListView;
import com.administrator.tradestock.model.ChiCangBean;
import com.administrator.tradestock.model.RemoveBean;
import com.administrator.tradestock.util.HttpManagerUtil;
import com.administrator.tradestock.util.SharePrenceUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ZhiJiaPingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZhiJiaPingFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;
    private SharedPreferences sp;
    private int page = 0;
    private TextView mNoData;
    private RemoveListAdapter adapter;
    private NoscrollListView mList;

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
        sp = SharePrenceUtil.getShareSaveUserInfo(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_zhi_jia_ping, container, false);
            initWidget();
            ZhiJiaPingAsyn zhiJiaPingAsyn = new ZhiJiaPingAsyn();
            zhiJiaPingAsyn.execute(sp.getString(SharePrenceUtil.OPEN_ID,""),""+page);
        }
        return mView;
    }

    private void initWidget(){
        mList = (NoscrollListView) mView.findViewById(R.id.remove_list);
        mNoData = (TextView) mView.findViewById(R.id.txt_no_data);

        adapter = new RemoveListAdapter(getContext());

    }



    /**
     *指价平仓单列表
     */
    private class ZhiJiaPingAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            RequestBody formBody = new FormBody.Builder()
                    .add("openid", strings[0])
                    .add("page", strings[1])
                    .build();
            String message =  HttpManagerUtil.getHttpManagerUtil().postHttpData(formBody,HttpManagerUtil.LOAD_PING_LIST);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!TextUtils.isEmpty(s)&&s.contains("serial_list")){
                Gson gson = new Gson();
                ChiCangBean chiCangBean = gson.fromJson(s,ChiCangBean.class);
                parsePingListData(chiCangBean);
            }else {
                showToast(s);
            }
        }
    }

    private void parsePingListData(ChiCangBean chiCangBean){
        List<ChiCangBean.SerialListBean> listBeen = chiCangBean.getSerial_list();
        if (listBeen != null){
            mNoData.setVisibility(View.GONE);

            List<RemoveBean> list = new ArrayList<>();
            for (int i = 0;i<listBeen.size();i++){
                ChiCangBean.SerialListBean serialListBean = listBeen.get(i);
                RemoveBean bean = new RemoveBean();
                bean.setGoodsName(serialListBean.getGoods_name());
                bean.setGoodsNum(serialListBean.getNums());
                bean.setTextNum(serialListBean.getSerial());
                //此处需要修改
                if (i%2 == 0){
                    bean.setIsBuy("1");
                }else {
                    bean.setIsBuy("2");
                }
                list.add(bean);
            }
            adapter.setData(list);
            mList.setAdapter(adapter);

        }else {
            mNoData.setVisibility(View.VISIBLE);
        }

    }
}
