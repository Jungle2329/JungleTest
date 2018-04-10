package com.zy.jungletest.activity.testActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zy.jungletest.R;

import java.lang.ref.WeakReference;

/**
 * Created by Jungle on 2018/3/5.
 */

public class AsyncTaskActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView text;
    private MyAsyncTask task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        text = findViewById(R.id.text);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new MyAsyncTask(AsyncTaskActivity.this);
                task.execute();
            }
        });

        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.cancel(true);
            }
        });
    }

    public static class MyAsyncTask extends AsyncTask<String, Integer, String> {

        private WeakReference<AsyncTaskActivity> mReference;

        private MyAsyncTask(AsyncTaskActivity mActivity) {
            mReference = new WeakReference<>(mActivity);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                int count = 0;
                while (count <= 100) {
                    count++;
                    publishProgress(count);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mReference.get().progressBar.setProgress(values[0]);
            mReference.get().text.setText("已下载" + values[0] + "%");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mReference.get().text.setText("准备开始了");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mReference.get().text.setText("加载完毕");
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
//            mReference.get().text.setText("已取消" + s + "了");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            mReference.get().text.setText("已取消");
        }
    }
}
