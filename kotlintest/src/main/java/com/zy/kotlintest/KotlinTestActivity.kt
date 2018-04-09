package com.zy.kotlintest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class KotlinTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)
        d()
    }

    fun d() {
        var a = 252
        a = a.plus(6)
        Log.i("fff", a.toString())
        if (a > 2) {
            a--
            Log.i("fff", a.toString())
        }
    }
}
