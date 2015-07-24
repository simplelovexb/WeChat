package com.cniao5.wechat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.cniao5.wechat.R;

/**
 * Created by acer on 2015/7/22.
 */
public class ClearEditText extends EditText implements View.OnFocusChangeListener,TextWatcher{

    private Drawable mClearDrawable;

    private boolean hasFocus;

    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){

        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null){

            mClearDrawable = getResources().getDrawable(R.drawable.selector_ic_delete);
        }

        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        //默认设置隐藏图标
        setClearIconVisible(true);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }


    protected void setClearIconVisible(boolean visible){
        Drawable right = visible ? mClearDrawable:null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus){
            setClearIconVisible(getText().length()>0);
        }else{
            setClearIconVisible(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFocus){
            setClearIconVisible(getText().length()>0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP){

            //是否点击在图标的坐标上
            if (getCompoundDrawables()[2]!=null){
                boolean touchable = event.getX()>(getWidth()-getTotalPaddingRight())
                        &&(event.getX()<((getWidth() - getPaddingBottom())));
                if (touchable){
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public void setShakeAnimation(){
        this.setAnimation(shakeAnimation(5));
    }

    private   Animation shakeAnimation(int counts){
        Animation translateAnimation = new TranslateAnimation(0,10,0,0);

        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }
}
