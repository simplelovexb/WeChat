package com.cniao5.wechat.http;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2015/7/22.
 */
public class BaseResponse {

    private Integer status;

    private String msg;

    private String data;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static final int SUCCESS = 1;
    public static final int ERROR = 0;

    public <T> T getObject(Class<T> clz){
        if (TextUtils.isEmpty(data)){
            return null;
        }
        return gson.fromJson(data,clz);
    }

    public <T> List<T> getList(Class<T> clz){
        if (TextUtils.isEmpty(data)){
            return null;
        }
        List<T> list = new ArrayList<T>();

        Type listType = type(List.class,clz);
        list = gson.fromJson(data,listType);
        return list;
    }

    public boolean isSuccess(){
        return status == SUCCESS;
    }

    static ParameterizedType type(final Class raw,final Type ... args)
    {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getOwnerType() {
                return raw;
            }

            @Override
            public Type getRawType() {
                return null;
            }
        };
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getStatus() {

        return status;
    }

    public String getMsg() {
        return msg;
    }

    public String getData() {
        return data;
    }
}
