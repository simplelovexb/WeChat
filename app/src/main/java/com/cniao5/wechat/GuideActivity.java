package com.cniao5.wechat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by acer on 2015/7/20.
 */
public class GuideActivity extends Activity {
    ArrayList<View> mList = new ArrayList<>();

    private int[] imgId = new int[]{
        R.drawable.img_guide_background_1,R.drawable.img_guide_background_2,
        R.drawable.img_guide_background_3,R.drawable.img_guide_background_4
    };

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager)findViewById(R.id.guide_viewpage);

        LayoutInflater inflater = getLayoutInflater();

        for (int i = 0 ; i<imgId.length ; i++){
            View view = inflater.inflate(R.layout.item_guide,null);
            ImageView imageView = (ImageView)view.findViewById(R.id.guide_img);
            imageView.setBackgroundResource(imgId[i]);
            if (i == imgId.length-1){
                ImageButton imageButton = (ImageButton)view.findViewById(R.id.guide_imb);
                imageButton.setVisibility(View.VISIBLE);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(GuideActivity.this,MainActivity.class));
                        finish();
                    }
                });
            }
            mList.add(view);
        }

        viewPager.setOffscreenPageLimit(2);//����Ԥ����
        viewPager.setAdapter(new MyPagerAdapter());

    }
    protected  class MyPagerAdapter extends  PagerAdapter{

        public Object instantiateItem(ViewGroup container, int position) {

            View view = mList.get(position);
            viewPager.addView(view);
            return position;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        public int getCount() {
            return mList.size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == mList.get((int)Integer.parseInt(object.toString()));
        }
    }

}
