package com.zy.jungletest.layoutManager.testLayoutManager;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.zy.commonlibrary.utils.AppUtils;

/**
 * Created by Jungle on 2018/5/31 0031.
 *
 * @desc TODO
 */

public class WaterFallFlowLayoutManager extends GridLayoutManager {

    private int totalHeight;
    private int verticalScrollOffset;
    private int spanCount = 1;
    private Context context;

    public WaterFallFlowLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public WaterFallFlowLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
        this.spanCount = spanCount;
        this.context = context;
    }

    public WaterFallFlowLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        this.spanCount = spanCount;
        this.context = context;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);

        detachAndScrapAttachedViews(recycler);
//        calculateItemSize(recycler);
    }

    private void calculateItemSize(RecyclerView.Recycler recycler) {
        totalHeight = 0;
        for (int i = 0; i < getItemCount(); i++) {
            // 遍历Recycler中保存的View取出来
            View view = recycler.getViewForPosition(i);
            addView(view); // 因为刚刚进行了detach操作，所以现在可以重新添加
            measureChildWithMargins(view, 0, 0); // 通知测量view的margin值
            int width = getDecoratedMeasuredWidth(view); // 计算view实际大小，包括了ItemDecorator中设置的偏移量。
            int height = getDecoratedMeasuredHeight(view);

            Rect mTmpRect = new Rect();
            //调用这个方法能够调整ItemView的大小，以除去ItemDecorator。
            calculateItemDecorationsForChild(view, mTmpRect);

            // 调用这句我们指定了该View的显示区域，并将View显示上去，此时所有区域都用于显示View，
            //包括ItemDecorator设置的距离。

            if (i % spanCount == 0) {
//                layoutDecorated(view, 0, totalHeight, width, totalHeight + height);
                layoutDecoratedWithMargins(view, 0, totalHeight, AppUtils.getWindowWidth(context) / 2,
                        totalHeight + height);
            } else {
                layoutDecoratedWithMargins(view, AppUtils.getWindowWidth(context) / 2, totalHeight,
                        AppUtils.getWindowWidth(context), totalHeight + height);
                totalHeight = totalHeight + height;

//                layoutDecorated(view, 0, totalHeight, width, totalHeight + height);
            }

            totalHeight += height;
        }
    }
}
