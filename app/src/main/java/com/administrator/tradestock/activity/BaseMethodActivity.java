package com.administrator.tradestock.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/5.
 */
public class BaseMethodActivity extends AppCompatActivity {
    private ConnectivityManager connectivityManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置所有屏幕竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkNetConnection();
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    private void checkNetConnection() {
        if (!isConnected(this)) {
            showToast("请连接网络！");
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        checkNetConnection();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    /**
     * 加载的进度条
     *
     * @param ev
     * @return
     */
    protected ProgressDialog mAlertDialog;

    /**
     * 四个重载的显示进度条的方法
     *
     * @return
     */
    public ProgressDialog showProgressDialog() {
        return showProgressDialog("", "请稍后", null);
    }

    protected ProgressDialog showProgressDialog(String pTitle, String pMessage) {
        return showProgressDialog(pTitle, pMessage, null);
    }

    protected ProgressDialog showProgressDialog(String pTitle) {
        return showProgressDialog(pTitle, "请稍后", null);
    }

    public synchronized ProgressDialog showProgressDialog(String pTitle, String pMessage,
                                                          DialogInterface.OnCancelListener pCancelClickListener) {
        if (mAlertDialog != null) {
            mAlertDialog.setTitle(pTitle);
            mAlertDialog.setMessage(pMessage);
            return mAlertDialog;
        }
        mAlertDialog = ProgressDialog.show(this, pTitle, pMessage, true, true);
        mAlertDialog.setCancelable(true);
        mAlertDialog.setOnCancelListener(pCancelClickListener);

        return (ProgressDialog) mAlertDialog;
    }

    /**
     * 取消进度条
     */
    public synchronized void cancelProgressDialog() {
        if (mAlertDialog != null) {
            if (mAlertDialog.isShowing()) {
                mAlertDialog.cancel();
            }
            mAlertDialog = null;
        }
    }

    private Toast mInfoToast;

    /**
     * 显示toast对话框
     *
     * @param msg
     */
    public void showToast(CharSequence msg) {
        if (msg == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        } else {
            if (mInfoToast == null) {
                mInfoToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
            }
            mInfoToast.cancel();
            mInfoToast.setText(msg);
            mInfoToast.show();
        }
    }


    //--------------------------------------------------------处理键盘事件--------------------------------------------------------------
    // 获取点击事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
