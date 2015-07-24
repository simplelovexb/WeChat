package com.cniao5.wechat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cniao5.wechat.widget.ClearEditText;

/**
 * Created by acer on 2015/7/22.
 */
public class Login extends Activity {
    ClearEditText editText ;
    Button button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText = (ClearEditText)findViewById(R.id.edt);
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setShakeAnimation();

            }
        });
    }
}
