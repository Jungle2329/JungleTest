package com.zy.jungletest.activity.testActivity;

import android.annotation.SuppressLint;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zy.jungletest.R;
import com.zy.jungletest.base.BaseActivity;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Jungle on 2018/9/21 0021.
 *
 * @desc TODO
 */

public class RxJavaLearnActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    TextView tv_content;
    public int a = 10;

    @Override
    protected int getViewId() {
        return R.layout.activity_rxjava;

    }

    @Override
    protected void initView() {
        Runnable race2 = () -> System.out.println("Hello world !");
        new Thread(() -> System.out.println("Hello world !")).start();
        new Thread(() -> {

        }).start();
    }

    interface MathOperation {
        int operation(int b);
    }

    @Override
    protected void initData() {
        MathOperation addition = (b) -> a + b;
        addition.operation(1);
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.btn_start})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
//                doRx();
//                doRx1();
                doRx2();
                break;
        }
    }

    /**
     * 最基本的用法
     */
    private void doRx() {
        clearContent();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {

            private Disposable mDisposable;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                addContent(integer);
                i++;
                if (i == 2) {
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                addContent("onError : value : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                addContent("onComplete");
            }
        });
    }

    /**
     * 使用Map变换
     * {@link com.zy.jungletest.activity.MainActivity}
     */
    private void doRx1() {
        clearContent();
        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onNext(2);
                        e.onNext(3);
                        e.onNext(4);
                        if (Looper.myLooper() == Looper.getMainLooper()) {
                            Log.i("zhangyi", "mainThread");
                        } else {
                            Log.i("zhangyi", "ioThread");
                        }
                    }
                })
                .subscribeOn(Schedulers.io())//发射器在子线程执行
                .observeOn(AndroidSchedulers.mainThread())//观察者（消费者）在主线程执行
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        addContent("doOnNext1 -- " + integer);
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer + 10000;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        if (Looper.myLooper() == Looper.getMainLooper()) {
                            addContent("mainThread");
                        } else {
                            addContent("ioThread");
                        }
                        addContent("subscribe -- " + integer);
                    }
                });
    }

    /**
     * 使用flatMap实现第二个依赖第一个
     */
    private void doRx2() {
        clearContent();
        Observable
                .just(1)
                .flatMap(integer -> Observable.just(integer + 1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        addContent(String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void doRx3() {
        Observable.defer(new ObservableSource<>() {
            @Override
            public void subscribe(Observer<> observer) {

            }
        })
    }

    @SuppressLint("SetTextI18n")
    private void addContent(String str) {
        tv_content.setText(tv_content.getText() + str + "\n");
    }

    @SuppressLint("SetTextI18n")
    private void addContent(int str) {
        tv_content.setText(tv_content.getText() + String.valueOf(str) + "\n");
    }

    private void clearContent() {
        tv_content.setText("");
    }

}
