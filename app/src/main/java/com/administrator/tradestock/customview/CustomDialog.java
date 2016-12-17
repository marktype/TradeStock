package com.administrator.tradestock.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.administrator.tradestock.R;


/**
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 * @author Tom.Cai
 */
public class CustomDialog extends Dialog {
    private TextView title,message;

    public CustomDialog(Context context) {
        super(context, R.style.myDialog);
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.card_hint_dialog_layout, null);
        message = (TextView) mView.findViewById(R.id.confirm_txt);   //消息
        super.setContentView(mView);
    }

    public View getMessageText(){
        return message;
    }

    public void setMessageText(String text){
        message.setText(text);
    }
    @Override
    public void setContentView(int layoutResID) {
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }

    /**
     * 确定键监听器 
     * @param listener
     */
    public void setOnConfirmListener(View.OnClickListener listener){
        message.setOnClickListener(listener);
    }


}
