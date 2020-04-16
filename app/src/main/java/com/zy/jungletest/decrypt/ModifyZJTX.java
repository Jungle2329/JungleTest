package com.zy.jungletest.decrypt;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;
import com.zy.jungletest.api.ApiRetrofit;
import com.zy.quickretrofit.MyLoggerInterceptor;

import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Jungle on 2019/12/2 0002.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */
public class ModifyZJTX {

    public static final String BASE_URL_NET = "https://newzjtxnew.zgzjzj.com/app/api/";


    private static final String CHAR_SET = "UTF-8";
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static String KEY = "%&^#hdyel1234f86";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void excuteAll() {
//        long startTime = 1575277567321L;
//        long endTime = System.currentTimeMillis();
//        long partTime = endTime - startTime;
//        long partTime2 = startTime + partTime;
//        long partTime3 = startTime + startTime + startTime;
//        excute(startTime, partTime2, (int) partTime, (int) partTime);

        long startTime = 1575278521362L;
        long endTime = System.currentTimeMillis();
        excute(startTime, endTime, 33340, 33340);
//        excute(partTime2, partTime3, (int) partTime / 3, (int) partTime / 3);
//        excute(partTime3, endTime, (int) partTime / 3, (int) partTime / 3);
    }

    /**
     * https://newzjtxnew.zgzjzj.com/app/api/class/app/classLibrary/classlookInfo
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void excute(long startTime, long endTime, int time, int lookTime) {
        Log.i("zhangyi", startTime + "startTime");
        Log.i("zhangyi", endTime + "endTime");
        Log.i("zhangyi", time + "time");
        Log.i("zhangyi", lookTime + "lookTime");
        int cid = 649;
        int csid = 1629;
        int upid = 3119492;
        int pid = 572;
        BaseModelReq baseModelReq = new BaseModelReq();
        HashMap hashMap = new HashMap();
        hashMap.put("cid", cid);
        hashMap.put("csid", csid);
        hashMap.put("upid", upid);
        hashMap.put("pid", pid);
        hashMap.put("time", time);
        hashMap.put("startTime", startTime);
        hashMap.put("endTime", endTime);
        hashMap.put("lookTime", lookTime);
        try {
            String secret = encrypt(KEY, new Gson().toJson(hashMap));
            String part1 = secret.substring(0, 76);
            String part2 = secret.substring(76, 152);
            String part3 = secret.substring(152);
            baseModelReq.data = part1 + "\n" + part2 + "\n" + part3 + "\n";

        } catch (Exception e) {
            e.printStackTrace();
        }

        MyTrustManager myTrustManager = new MyTrustManager();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(MyTrustManager.createSSLSocketFactory())
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new MyLoggerInterceptor(""))
                .addInterceptor(new BusinessRequestInterceptor())
                .build();

        Retrofit r = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL_NET)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        r.create(ApiRetrofit.class)
                .recordCourseTime(baseModelReq)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            Log.i("zhangyi", responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encrypt(String str, String str2) throws Exception {
        if (str == null || str.length() != 16) {
            return null;
        }
        Key secretKeySpec = new SecretKeySpec(str.getBytes("utf-8"), "AES");
        Cipher str1 = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        str1.init(1, secretKeySpec);
        return Base64.getEncoder().encodeToString(str1.doFinal(str2.getBytes("utf-8")));
    }


    /**
     * AES解密操作
     *
     * @param encryptContent 加密的密文
     * @param password       解密的密钥
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decrypt(String encryptContent, String password) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //设置为解密模式
            Key secretKeySpec = new SecretKeySpec(password.getBytes("utf-8"), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            //执行解密操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(encryptContent));
            return new String(result, CHAR_SET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
