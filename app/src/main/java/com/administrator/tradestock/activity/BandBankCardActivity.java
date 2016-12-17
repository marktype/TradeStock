package com.administrator.tradestock.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.administrator.tradestock.R;
import com.administrator.tradestock.customview.CustomDialog;

public class BandBankCardActivity extends BascActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tintManager.setStatusBarTintColor(getResources().getColor(R.color.app_yellow));
        setContentView(R.layout.activity_band_bank_card);
        initWidget();
    }

    private void initWidget(){
        ImageView mBackImg= (ImageView) findViewById(R.id.back_img);
        ImageView mProblem = (ImageView) findViewById(R.id.problem_img);

        mBackImg.setOnClickListener(this);
        mProblem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.problem_img:
                getDialog();
                break;
        }
    }


    /**
     * 弹框
     */
    public void getDialog(){
        final CustomDialog dialog = new CustomDialog(this);
        dialog.show();
        dialog.setOnConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
