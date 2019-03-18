package com.zy.jungletest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jungle on 2019/2/27 0027.
 *
 * @author JungleZhang
 * @version 1.0.0 初始化
 * @version 1.0.1 优化了在换行位置是最后一个时导致显示错误的问题，完善子元素Gone后的显示
 * @Description 流布局
 */

public class FlowLayout extends ViewGroup {


    private List<ChildLayout> childLayouts = new ArrayList<>();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        // 高度增加是两个中情况，
        // 1.换行后把上一行最大高度算入
        // 2.最后个数据的时候把当前最大高度算入
        int totalHeight = 0;
        int totalWidth = 0;
        int childWidth;
        int childHeight;
        int currentMaxHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            //当前child的宽度
            childWidth = child.getMeasuredWidth();
            childHeight = child.getMeasuredHeight();
            if (child.getVisibility() != View.GONE) {
                if ((totalWidth + childWidth) > widthSize) {// 需要换行
                    // 条件1 换行之后把上一行最大值算入
                    totalHeight += currentMaxHeight;
                    // 重置宽度
                    totalWidth = 0;
                    // 重置当前最大高度
                    currentMaxHeight = childHeight + params.topMargin + params.bottomMargin;
                    childLayouts.add(new ChildLayout(child, totalWidth + params.leftMargin, totalHeight + params.topMargin, childWidth, childHeight));
                } else {// 不换行
                    childLayouts.add(new ChildLayout(child, totalWidth + params.leftMargin, totalHeight + params.topMargin, childWidth, childHeight));
                    currentMaxHeight = currentMaxHeight > childHeight + params.topMargin + params.bottomMargin ? currentMaxHeight
                            : childHeight + params.topMargin + params.bottomMargin;
                }
                totalWidth = totalWidth + childWidth + params.leftMargin + params.rightMargin;
            }
            if (i == getChildCount() - 1) {// 条件2 最后一条数据需要把当前行高算进去，且最后一个无视是否可见
                totalHeight += currentMaxHeight;
            }
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize);
        } else {
            setMeasuredDimension(widthSize, totalHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (ChildLayout layout : childLayouts) {
            layout.child.layout(layout.getL(), layout.getT(), layout.getR(), layout.getB());
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


    class ChildLayout {
        private View child;
        private int l;
        private int t;
        private int r;
        private int b;
        private int width;
        private int height;

        ChildLayout(View child, int l, int t, int width, int height) {
            this.child = child;
            this.width = width;
            this.height = height;
            this.l = l;
            this.t = t;
            this.r = l + width;
            this.b = t + height;

        }

        public View getChild() {
            return child;
        }

        public void setChild(View child) {
            this.child = child;
        }

        public int getL() {
            return l;
        }

        public void setL(int l) {
            this.l = l;
        }

        public int getT() {
            return t;
        }

        public void setT(int t) {
            this.t = t;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
