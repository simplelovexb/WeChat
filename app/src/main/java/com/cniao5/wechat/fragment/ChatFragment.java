package com.cniao5.wechat.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cniao5.wechat.R;
import com.cniao5.wechat.widget.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by acer on 2015/7/19.
 */
public class ChatFragment extends Fragment {
    private RequestQueue mQueue;
    private Button button;
    private ImageView imageView;
    private LoadingDialog loadingDialog;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_chat, container, false);

        imageView = (ImageView)view.findViewById(R.id.frg_chat_img);
        button = (Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.setMessage("正在加载");
        mQueue = Volley.newRequestQueue(getActivity());
    }

    public ImageRequest getImageRequest(){
        String URL = "http://b6.photo.store.qq.com/psb?/V14BR5RG4fX2LG/q14bGsV**7GYnZsPduvwag" +
                "4x.FAAlm0g3S0G3ziOS*c!/b/dAYAAAAAAAAA&bo=lAEgApQBIAIDACU!&rf=viewer_4&t=5";
        loadingDialog.show();
        ImageRequest request = new ImageRequest(URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
                loadingDialog.dismiss();
            }
        },200,200, Bitmap.Config.RGB_565, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });
        return  request;
    }


    public StringRequest getStringRequest(){
        String URL = "http://www.baidu.com";
        loadingDialog.show();
        final StringRequest request = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),response,Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("ChatFragment", error.toString());
                loadingDialog.dismiss();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String > map = new HashMap<>(1);
                map.put("key","v");
                return  map;
            }
        };
        return request;
    }

    public JsonObjectRequest getJsonRequest(){
        String URL = "http://www.weather.com.cn/data/sk/101280101.html";
        loadingDialog.show();
        JsonObjectRequest request = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                loadingDialog.dismiss();
                try {
                    Log.d("Tag",response.getJSONObject("weatherinfo").getString("city")) ;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                loadingDialog.dismiss();
                Log.d("ERROR", error.toString());
            }
        });
        return request;
    }

}


