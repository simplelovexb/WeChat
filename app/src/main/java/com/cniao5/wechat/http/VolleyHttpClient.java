package com.cniao5.wechat.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cniao5.wechat.widget.LoadingDialog;

import java.util.Map;

import cn.trinea.android.common.util.ToastUtils;

/**
 * Created by acer on 2015/7/22.
 */
public class VolleyHttpClient {
    private VolleySingleton volleySingleton;
    private static VolleyHttpClient mInstance;
    private LoadingDialog dialog ;
    private Context mContext;
    public static synchronized VolleyHttpClient getmInstance(Context context){
        if (mInstance == null){
            mInstance = new VolleyHttpClient(context);
        }
        return mInstance;
    }

    public void post(String url,
                     Map<String ,String> params ,
                     int loadingMsg,
                     final RequestListener listener){
        request(Request.Method.POST,url,params,loadingMsg,listener);
    }

    public void get(String url,
                     int loadingMsg,
                     final RequestListener listener){
        request(Request.Method.GET,url,null,loadingMsg,listener);
    }

    private  VolleyHttpClient(Context context){
        mContext = context;
        volleySingleton = VolleySingleton.getInstance(context);
        dialog = new LoadingDialog(context);
    }

    public void request(int method,String url,
                        Map<String,String> params,int loadingMsg,
                        final RequestListener listener){
        if (listener!=null){
            listener.onPreRequest();
        }
        showDialog(loadingMsg);
        BaseRequest request = new BaseRequest(method,
                url,
                params,
                new Response.Listener<BaseResponse>() {
                    public void onResponse(BaseResponse response) {
                        dismissDialog();
                        if (listener!=null) {
                            if (response.isSuccess()) {
                                listener.onRequestSuccess(response);
                            } else {
                                listener.onRequestFail(response.getStatus(),response.getMsg());
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        dismissDialog();
                        String errMsg = VolleyErrorHelper.getMessage(mContext,error);
                        ToastUtils.show(mContext,errMsg);
                        if (listener!=null) {
                            listener.onRequestError(error.networkResponse.statusCode,errMsg);
                        }
                    }
                }
        );

        volleySingleton.addToRequestQueue(request);
    }

    private void showDialog (int msg){
        if (msg>0){
            dialog.setMessage(msg);
            dialog.show();
        }
    }

    private void dismissDialog(){
        if (dialog.isShow()){
            dialog.dismiss();
        }
    }
}
