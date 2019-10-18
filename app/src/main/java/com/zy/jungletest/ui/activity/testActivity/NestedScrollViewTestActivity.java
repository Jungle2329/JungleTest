package com.zy.jungletest.ui.activity.testActivity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zy.jungletest.R;
import com.zy.jungletest.base.BaseActivity;
import com.zy.jungletest.ui.fragment.NestedAFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Jungle on 2018/4/25 0025.
 *
 * @desc TODO
 */

public class NestedScrollViewTestActivity extends BaseActivity {

    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected int getViewId() {
        return R.layout.activity_nested_scroll;
    }

    @Override
    protected void initView() {
        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(new NestedAFragment());
        mPagerAdapter.addFragment(new NestedAFragment());
        mPagerAdapter.addFragment(new NestedAFragment());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    class PagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
            list = new ArrayList<>();
        }

        public PagerAdapter addFragment(Fragment f) {
            list.add(f);
            return this;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "测试" + position;
        }
    }
}
