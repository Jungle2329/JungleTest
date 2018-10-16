package com.zy.jungletest;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zy.jungletest.database.DatabaseHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testSelect() throws Exception {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query("ldcang", null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {

            int personid = cursor.getInt(0); //获取第一列的值,第一列的索引从0开始

            String name = cursor.getString(1);//获取第二列的值

            int age = cursor.getInt(2);//获取第三列的值

            String flag = cursor.getString(3);

            String phone = cursor.getString(4);

            Log.i("zhangyi", "personid: " + personid);
            Log.i("zhangyi", "name: " + name);
            Log.i("zhangyi", "age: " + age);
            Log.i("zhangyi", "flag: " + flag);
            Log.i("zhangyi", "phone: " + phone);
        }

        cursor.close();
        db.close();
    }

    public void testInsert() throws Exception {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", "fanyujie");
        cv.put("age", 24);
        cv.put("flag", "false");
        cv.put("phone", "13753153915");
        db.insert("ldcang", null, cv);

        db.close();
    }

    public void testNormal() {
        Log.i("test","123");
    }
}