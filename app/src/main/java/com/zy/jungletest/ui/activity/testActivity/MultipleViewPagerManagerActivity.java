package com.zy.jungletest.ui.activity.testActivity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.zy.jungletest.R;
import com.zy.jungletest.base.BaseActivity;
import com.zy.jungletest.ui.fragment.NestedAFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Jungle on 2018/8/21 0021.
 *
 * @desc TODO
 */

public class MultipleViewPagerManagerActivity extends BaseActivity {

    @BindView(R.id.data_pager)
    ViewPager viewpager;
    @BindView(R.id.tab_layout)
    TabLayout tab;


    @Override
    protected int getViewId() {
        return R.layout.activity_multiple_viewpager_manager;
    }

    @Override
    protected void initView() {
        InnerAdapter mPagerAdapter = new InnerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(new NestedAFragment());
        mPagerAdapter.addFragment(new NestedAFragment());
        mPagerAdapter.addFragment(new NestedAFragment());
        mPagerAdapter.addFragment(new NestedAFragment());
        mPagerAdapter.addFragment(new NestedAFragment());
        viewpager.setAdapter(mPagerAdapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.setupWithViewPager(viewpager);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    class InnerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> list;

        public InnerAdapter(FragmentManager fm) {
            super(fm);
            list = new ArrayList<>();
        }

        public InnerAdapter addFragment(Fragment f) {
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
