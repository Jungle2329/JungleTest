package com.zy.jungletest.ui.activity.testActivity;

import android.content.Context;

import com.zy.commonlibrary.base.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Jungle on 2019/3/29 0029.
 *
 * @author JungleZhang
 * @version 1.0.0
 * @Description
 */
public class JsonFileLoader {

    private Context mContext;
    private int resId;

    public JsonFileLoader(Context mContext, int resId) {
        this.mContext = mContext;
        this.resId = resId;
    }

    public String fileLoader() {
        InputStream is = mContext.getResources().openRawResource(resId);
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);
        StringBuffer sb = new StringBuffer();
        String str;
        try {
            while (null != (str = br.readLine())) {
                sb.append(str);
                sb.append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void getJson() throws JSONException {
        String jsonData = fileLoader();
        JSONObject object = new JSONObject(jsonData);
        LogUtils.i("zhangyi", (String) object.get("name"));
    }
}
