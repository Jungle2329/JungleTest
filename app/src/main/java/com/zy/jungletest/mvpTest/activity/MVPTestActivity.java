package com.zy.jungletest.mvpTest.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.zy.jungletest.R;
import com.zy.jungletest.model.TranslationBean;
import com.zy.jungletest.mvpTest.mvpInterface.MvpView;
import com.zy.jungletest.mvpTest.presenter.MVPPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.adapter.rxjava2.Result;


/**
 * Created by Jungle on 2018/4/16 0016.
 */

public class MVPTestActivity extends AppCompatActivity implements MvpView {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.aet_input)
    AppCompatEditText aet_input;

    private ProgressDialog progressDialog;
    private MVPPresenter mMVPPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvptest);
        ButterKnife.bind(this);

        // 初始化进度条
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在加载数据");

        mMVPPresenter = new MVPPresenter(this, this);
    }

    @OnClick(R.id.getData)
    public void getData() {
        String trsWords = aet_input.getText().toString().trim();
        if(TextUtils.isEmpty(trsWords)) {
            return;
        }
        mMVPPresenter.getData(trsWords);
    }

    @Override
    public void showLoading() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showData(Result<TranslationBean> data) {
        text.setText(data.response().body().getTranslateResult().get(0).get(0).getTgt());
    }

    @Override
    public void showFailureMessage(String msg) {
        text.setText(msg);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "网络请求数据出现异常", Toast.LENGTH_SHORT).show();
        text.setText("网络请求数据出现异常");
    }
}
