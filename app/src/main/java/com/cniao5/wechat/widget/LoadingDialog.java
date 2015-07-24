package com.cniao5.wechat.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cniao5.wechat.R;

/**
 * Created by acer on 2015/7/21.
 */
public class LoadingDialog {
    private Context mContext;
    private View mDialogView;
    private Dialog dialog;
    private TextView mTxtMsg;

    public LoadingDialog(Context mContext){
        this.mContext = mContext;
        mDialogView = (View) LayoutInflater.from(mContext).inflate(R.layout.dialog_loading, null);
        mTxtMsg = (TextView)mDialogView.findViewById(R.id.dialog_txt);

        initDialog();
    }


    private void initDialog (){
        dialog = new Dialog(mContext,R.style.dialog);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(mDialogView);
    }

    public void show(){
        dialog.show();;
    }

    public void dismiss(){
        dialog.dismiss();
    }

    public void  setMessage(CharSequence msg){
        mTxtMsg.setText(msg);
    }

    public void setMessage(int msg){
        mTxtMsg.setText(msg);
    }

    public boolean isShow(){
        return dialog.isShowing();
    }
}
