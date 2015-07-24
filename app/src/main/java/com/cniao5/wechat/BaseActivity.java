package com.cniao5.wechat;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;

import com.cniao5.wechat.http.VolleyHttpClient;

/**
 * Created by acer on 2015/7/22.
 */
public class BaseActivity extends ActionBarActivity {

    protected VolleyHttpClient mHttpClient;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mHttpClient = VolleyHttpClient.getmInstance(this);
    }
}
