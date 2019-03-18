package com.zy.jungletest.activity.testActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zy.commonlibrary.utils.AppUtils;
import com.zy.jungletest.R;
import com.zy.jungletest.base.BaseActivity;
import com.zy.jungletest.layoutManager.FlowLayoutManager;

import butterknife.BindView;

/**
 * Created by Jungle on 2018/5/30 0030.
 *
 * @desc LayoutManager学习
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
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.setLayoutManager(new WaterFallFlowLayoutManager(this, 2));
        mRecyclerView.setLayoutManager(new FlowLayoutManager());
//        mRecyclerView.addItemDecoration(new MyItemDecoration());
        mRecyclerView.setAdapter(new InnerAdapter());
    }

    // 自定义InteDecoration
    class MyItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
            c.save();
            Paint mPaint = new Paint();
            mPaint.setColor(Color.BLACK);
            c.drawText("123123123", 0, 0, mPaint);
            c.restore();
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
            c.save();
            Paint mPaint = new Paint();
            mPaint.setColor(Color.BLACK);
            c.drawText("321321321", 0, 0, mPaint);
            c.restore();
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, 0, 100, 100);
        }
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

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(LayoutManagerTestActivity.this).inflate(R.layout.item_skid_right_1, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.img_bg.setBackgroundColor(colors[position % 5]);
            holder.fl_parent.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, AppUtils.dip2px(LayoutManagerTestActivity.this, heights[position % 2])));
            holder.tv_title.setText(position + "");
        }

        @Override
        public int getItemCount() {
            return 300;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img_bg;
            FrameLayout fl_parent;
            TextView tv_title;

            public ViewHolder(View itemView) {
                super(itemView);
                img_bg = itemView.findViewById(R.id.img_bg);
                fl_parent = itemView.findViewById(R.id.fl_parent);
                tv_title = itemView.findViewById(R.id.tv_title);
            }
        }
    }


    class ApiException extends Exception {
        public ApiException(Throwable throwable, int code) {
            super(throwable);
        }
    }
}
