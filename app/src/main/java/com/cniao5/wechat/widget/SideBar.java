package com.cniao5.wechat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by acer on 2015/7/24.
 */
public class SideBar extends View {

    private OnTouchingLetterChangeListener mListener;
    private static String[] chars = {
      "A","B","C","D","E","F","G","H","I","J","K","L","M","N",
            "O","P","Q","R","S","T","U","V","W","X","Y","Z","#"
    };
    private int choose = -1;//бЁжа
    private Paint paint = new Paint();
    private TextView mTextDialog;

    public void setTextView(TextView mTextDialog){

        this.mTextDialog = mTextDialog;

    }


    public SideBar(Context context){
        super(context);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height = getHeight();
        int width = getWidth();
        int singleHeight = height/chars.length;

        for (int i = 0 ; i <chars.length; i++){
            paint.setColor(Color.rgb(33, 65, 98));

            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(30);
            if (i == choose){
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
            }
            float xPos = width/2 - paint.measureText(chars[i])/2;

            float yPos = singleHeight*i +singleHeight;
            canvas.drawText(chars[i],xPos,yPos,paint);
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangeListener listener = mListener;
        final int c =(int)(y/getHeight()*chars.length);
        switch (action){
            case MotionEvent.ACTION_UP:
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;
                invalidate();
                if (mTextDialog != null){
                   // mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                if (oldChoose != c){
                    if (listener != null) {
                        listener.onTouchingLetterChange(chars[c]);
                    }
                    if (c>=0&&c<chars.length){
                        if (mTextDialog != null){
                            mTextDialog.setText(chars[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choose = c;
                        invalidate();
                    }
                }
                break;
        }


        return true;
    }

    public void setOnTouchingLetterChangeListener(
            OnTouchingLetterChangeListener onTouchingLetterChangeListener){
        this.mListener = onTouchingLetterChangeListener;
    }



    public interface  OnTouchingLetterChangeListener{
        public void onTouchingLetterChange(String s);
    }
}
