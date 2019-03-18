package com.zy.jungletest.layoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Jungle on 2019/2/27 0027.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description 自定义流布局管理器
 */

public class FlowLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        for (int i = 0; i < getItemCount(); i++) {
//            recycler.getViewForPosition(i).layout
        }

    }
}
