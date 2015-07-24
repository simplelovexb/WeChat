package com.cniao5.wechat.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by acer on 2015/7/22.
 */

//本类采用单例设计模式
public class VolleySingleton {

    private RequestQueue mRequestQueue;
    private static VolleySingleton mInstance;

    public static synchronized VolleySingleton getInstance(Context mContext){
        if (mInstance==null){
            mInstance = new VolleySingleton(mContext);
        }
        return mInstance;
    }

    private VolleySingleton(Context mContext){

        mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
    }

    public RequestQueue getRequestQueue(){
        return this.mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

    public void cancleRequeue(Object tag){
        getRequestQueue().cancelAll(tag);
    }
}
