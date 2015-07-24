package com.cniao5.wechat.http;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by acer on 2015/7/22.
 */
public class BaseRequest extends Request<BaseResponse> {

    private Response.Listener<BaseResponse> mListener;
    private Map<String ,String > mParams;
    public BaseRequest(int method, String url,Map<String,String> parms,Response.Listener listener, Response.ErrorListener errorListenerr) {

        super(method,url,errorListenerr);
        mListener = listener;
        mParams = parms;
    }

    @Override
    protected Response<BaseResponse> parseNetworkResponse(NetworkResponse response) {

        try {
            String  jsonString = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));

            BaseResponse baseResponse = parseJson(jsonString);

            return Response.success(baseResponse,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void deliverResponse(BaseResponse response) {
        mListener.onResponse(response);
    }

    private BaseResponse parseJson(String json){
        int status = 0;
        String msg = null;
        String data = null;

        try{
            JSONObject jsonObject = new JSONObject(json);
            status = jsonObject.getInt("status");
            msg = jsonObject.getString("msg");
            data = jsonObject.getString("data");
        }catch (Exception e){
            e.printStackTrace();
        }

        BaseResponse response = new BaseResponse();
        response.setData(data);
        response.setMsg(msg);
        response.setStatus(status);
        return response;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
}
