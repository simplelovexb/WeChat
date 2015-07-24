package com.cniao5.wechat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.webkit.WebView;

/**
 * Created by acer on 2015/7/19.
 */
public class StartActivity extends Activity{
    private WebView mWebView;
    private String URL = "http://b6.photo.store.qq.com/psb?/V14BR5RG4fX2LG/q14bGsV**7GYnZ" +
            "sPduvwag4x.FAAlm0g3S0G3ziOS*c!/m/dAYAAAAAAAAAnull&bo=lAEgApQBIAIDCC0!&rf=photolist&t=5";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.loadUrl(URL);
        new Handler().postDelayed(new Runnable() {
            boolean isFirst;
            public void run() {
                SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                isFirst = sharedPreferences.getBoolean("isFirst",true);
                if (isFirst){
                    Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isFirst", false);
                    editor.commit();
                    Intent intent = new Intent(StartActivity.this,GuideActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(StartActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                finish();

            }
        },3000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }

        return super.onKeyDown(keyCode,event);
    }
}
