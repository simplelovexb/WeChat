package com.cniao5.wechat.http;

/**
 * Created by acer on 2015/7/22.
 */
public interface RequestListener {


    /**
     * 在请求之前调用的方法
     */
    public void onPreRequest();


    /**
     * 请求成功调用
     * @param response
     */
    public void onRequestSuccess(BaseResponse response);


    /**
     * 请求失败调用（致命失败）
     * @param code
     * @param msg
     */
    public void onRequestError(int code,String msg);


    /**
     * 服务器返回失败调用
     * @param code
     * @param msg
     */
    public void onRequestFail(int code,String msg);

}
