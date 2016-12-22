package com.administrator.tradestock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.adapter.BankCardAdapter;
import com.administrator.tradestock.model.BankInfoBean;

import java.util.ArrayList;
import java.util.List;

public class BandBankCardListActivity extends BascActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bank_list_layout);
        initWidget();
    }

    private void initWidget(){
        ImageView mBackImg= (ImageView) findViewById(R.id.back_img);
        TextView mAddCard = (TextView) findViewById(R.id.add_card_txt);
        ListView mList = (ListView) findViewById(R.id.bank_card_list);
        BankCardAdapter adapter = new BankCardAdapter(this);
        adapter.setData(getBankData());
        mList.setAdapter(adapter);

        mBackImg.setOnClickListener(this);
        mAddCard.setOnClickListener(this);

    }

    private  List<BankInfoBean> getBankData(){
        List<BankInfoBean> list = new ArrayList<>();
        for (int i =0;i<2;i++){
            BankInfoBean infoBean = new BankInfoBean();
            infoBean.setBankName("中国银行");
            infoBean.setBankNum("123464598798787987");
            list.add(infoBean);
        }
        return list;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.add_card_txt:
                Intent intent= new Intent(this,BandBankCardActivity.class);
                startActivity(intent);
                break;
        }
    }
}
