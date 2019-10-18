package com.zy.jungletest.ui.activity.testActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zy.jungletest.Java8.Car;
import com.zy.jungletest.R;
import com.zy.jungletest.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;

/**
 * Created by Jungle on 2018/9/21 0021.
 *
 * @desc TODO
 */

public class RxJavaLearnActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    TextView tv_content;
    public int a = 10;
    private long current = 0;

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
        current = System.currentTimeMillis();
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
//                doRx2();
                doRx3();
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
     * {@link com.zy.jungletest.ui.activity.MainActivity}
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

    private void haha(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 创建数据
     * fromArray
     * interval
     * just
     * range
     * create
     * empty/never/throw
     */
    private void doRx3() {

//        Observable.defer(() -> Observable.just(1))
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


    }

    private void createFromArray() {
        Observable.fromArray(new int[]{1, 2, 3, 4, 5})
                .timeInterval(TimeUnit.SECONDS)
                .subscribe(new Observer<Timed<int[]>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Timed<int[]> timed) {
                        clearContent();
                        addContent(Arrays.toString(timed.value()));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void createRange() {
        Observable.range(1, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        clearContent();
                        addContent(integer + "");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void createInterval() {
        Observable.interval(1, 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        current += 1000;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault());
                        String time = sdf.format(new Date(current));
                        clearContent();
                        addContent(time);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void java8() {
        List<Car> list = Collections.singletonList(Car.create(Car::new));
        list.forEach(Car::collide);
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
