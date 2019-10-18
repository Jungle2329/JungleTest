package com.zy.jungletest.ui.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zy.jungletest.R;
import com.zy.jungletest.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Jungle on 2018/4/25 0025.
 *
 * @desc TODO
 */

public class NestedAFragment extends BaseFragment {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private MyAdapter myAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_nestscroll;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        myAdapter = new MyAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(myAdapter);
    }


    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_nested, null);
            MyViewHolder holder = new MyViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv_text1.setText(position + "");
        }

        @Override
        public int getItemCount() {
            return 200;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_text1;

        private MyViewHolder(View itemView) {
            super(itemView);
            tv_text1 = itemView.findViewById(R.id.tv_text1);
        }

    }

}
