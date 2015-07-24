package com.cniao5.wechat.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by acer on 2015/7/21.
 */
public class GsonRequest<T> extends Request<T> {

    private Response.Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClass;
    private Map<String ,String > mParams;


    //初始化
    public GsonRequest(int method, String url,Class<T> classz,Response.Listener listener,
                       Response.ErrorListener errorListener,Map<String,String> params) {
        super(method, url, errorListener);
        mGson = new Gson();
        mClass = classz;
        mListener = listener ;
        mParams = params;
    }

    public GsonRequest(String URL,Class<T> classz,Response.Listener listener, Response.ErrorListener errorListener){
        this(Method.GET,URL,classz,listener,errorListener,null);
    }


    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(jsonString,mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
}
