package com.cniao5.wechat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.cniao5.wechat.fragment.ChatFragment;
import com.cniao5.wechat.fragment.FriendFragment;
import com.cniao5.wechat.fragment.MineFragment;
import com.cniao5.wechat.fragment.MonmentFragment;
import com.cniao5.wechat.sys.Constant;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private ArrayList<MyTab> myTabArrayList = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushManager.startWork(MainActivity.this, PushConstants.LOGIN_TYPE_API_KEY, Constant.BAI_DU_APP_KEY);
        myTabArrayList.add(new MyTab(getString(R.string.chart), ChatFragment.class));
        myTabArrayList.add(new MyTab(getString(R.string.contact), FriendFragment.class));
        myTabArrayList.add(new MyTab(getString(R.string.moment), MonmentFragment.class));
        myTabArrayList.add(new MyTab(getString(R.string.mine), MineFragment.class));

        init();
    }

    private void init(){
        mRadioGroup = (RadioGroup)findViewById(R.id.rg_bottom);
        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String tag = null;
                switch (checkedId){
                    case R.id.rb_chat:
                        mViewPager.setCurrentItem(0);
                        tag = "0";
                        break;
                    case R.id.rb_friend:
                        mViewPager.setCurrentItem(1);
                        tag = "1";
                        break;
                    case R.id.rb_moment:
                        mViewPager.setCurrentItem(2);
                        tag = "2";
                        break;
                    case R.id.rb_mine:
                        mViewPager.setCurrentItem(3);
                        tag = "3";
                        break;

                }
                Toast.makeText(MainActivity.this, tag + checkedId, Toast.LENGTH_SHORT).show();
            }
        });

        mViewPager.setAdapter(new FragmentViewPageAdapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float v, int i1) {

            }
            public void onPageSelected(int i) {


            }

            public void onPageScrollStateChanged(int i) {

            }
        });

    }



    class MyTab{
        private String text;

        private Class fragment;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setFragment(Class fragment) {
            this.fragment = fragment;
        }

        public Class getFragment() {
            return fragment;
     }

        public MyTab(String text, Class fragment) {
            this.text = text;
            this.fragment = fragment;
        }
    }

    class FragmentViewPageAdapter extends FragmentPagerAdapter{
        public FragmentViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            try {
                return (Fragment) myTabArrayList.get(i).getFragment().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return  null;
            }
        }

        @Override
        public int getCount() {
            return myTabArrayList.size();
        }
    }
}