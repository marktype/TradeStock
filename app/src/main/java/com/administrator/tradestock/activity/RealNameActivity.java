package com.administrator.tradestock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.administrator.tradestock.R;

public class RealNameActivity extends BascActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.app_yellow));
        setContentView(R.layout.activity_real_name);
        initWidget();
    }

    private void initWidget(){
        ImageView mBackImg= (ImageView) findViewById(R.id.back_img);

        mBackImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
        }
    }
}
