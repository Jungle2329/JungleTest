package com.zy.jungletest.ui.activity.testActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zy.jungletest.R;
import com.zy.jungletest.view.GridViewWithHeaderAndFooter;


/**
 * 测试加头尾的GridView
 * Created by Jungle on 2016/10/17.
 */

public class TestGridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_test);

        final GridViewWithHeaderAndFooter mHeaderGridView = (GridViewWithHeaderAndFooter) findViewById(R.id.hgv);
        mHeaderGridView.setNumColumns(5);
        TextView header = new TextView(this);
        header.setText("我是头部");
        header.setBackgroundColor(Color.BLUE);
        header.setGravity(Gravity.CENTER);

        TextView footer = new TextView(this);
        footer.setText("我是尾部");
        footer.setBackgroundColor(Color.RED);
        footer.setGravity(Gravity.CENTER);

        mHeaderGridView.addHeaderView(header);
        mHeaderGridView.addFooterView(footer);

        final EditText et_01 = (EditText) findViewById(R.id.et_01);
        Button bt_01 = (Button) findViewById(R.id.bt_01);

        bt_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHeaderGridView.setNumColumns(Integer.parseInt(et_01.getText().toString().trim()));
            }
        });

        mHeaderGridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 32;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView a = new TextView(TestGridViewActivity.this);
                a.setText("我是" + position);
                a.setGravity(Gravity.CENTER);

                return a;
            }
        });
    }
}
