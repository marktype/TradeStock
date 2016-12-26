package com.administrator.tradestock.fragment;


import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.adapter.RemoveListAdapter;
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
 */
public class RemoveListFragment extends BaseFragment {
    private View mView;
    private TextView mNoData;
    private ListView mList;
    private RemoveListAdapter adapter;
    private SharedPreferences sp;
    private int page = 0;

    public RemoveListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null){
            sp = SharePrenceUtil.getShareSaveUserInfo(getContext());
            mView= inflater.inflate(R.layout.fragment_remove_list_layout, container, false);
            initWidget();
            ZhiJiaPingAsyn zhiJiaPingAsyn = new ZhiJiaPingAsyn();
            zhiJiaPingAsyn.execute(sp.getString(SharePrenceUtil.OPEN_ID,""),""+page);
        }
        return mView;
    }


    private void initWidget(){
        mList = (ListView) mView.findViewById(R.id.remove_list);
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
                bean.setGoodsNum(serialListBean.getSerial());
                bean.setTextNum(serialListBean.getNums());
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
