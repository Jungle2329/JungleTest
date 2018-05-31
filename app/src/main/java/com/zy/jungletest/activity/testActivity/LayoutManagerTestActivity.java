package com.zy.jungletest.activity.testActivity;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zy.jungletest.R;
import com.zy.jungletest.base.BaseActivity;
import com.zy.jungletest.utils.AppUtils;

import butterknife.BindView;

/**
 * Created by Jungle on 2018/5/30 0030.
 *
 * @desc TODO
 */

public class LayoutManagerTestActivity extends BaseActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected int getViewId() {
        return R.layout.activity_layout_manager;
    }

    @Override
    protected void initView() {
//        mRecyclerView.setLayoutManager(new SkidRightLayoutManager(1.5f, 0.85f));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.setLayoutManager(new WaterFallFlowLayoutManager(this, 2));
        mRecyclerView.setAdapter(new InnerAdapter());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    class InnerAdapter extends RecyclerView.Adapter<InnerAdapter.ViewHolder> {

        int[] colors = {Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA, Color.YELLOW};
        String[] colorStrs = {"BLUE", "RED", "GREEN", "MAGENTA", "YELLOW"};
        int[] heights = {50, 150};

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(LayoutManagerTestActivity.this).inflate(R.layout.item_skid_right_1, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.img_bg.setBackgroundColor(colors[position % 5]);
            holder.card_view.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, AppUtils.dip2px(LayoutManagerTestActivity.this, heights[position % 2])));
            holder.tv_title.setText(position + "");
        }

        @Override
        public int getItemCount() {
            return 300;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img_bg;
            CardView card_view;
            TextView tv_title;

            public ViewHolder(View itemView) {
                super(itemView);
                img_bg = itemView.findViewById(R.id.img_bg);
                card_view = itemView.findViewById(R.id.card_view);
                tv_title = itemView.findViewById(R.id.tv_title);
            }
        }
    }
}
