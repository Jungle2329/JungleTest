package com.zy.jungletest.api

import android.content.Context
import android.widget.Toast

/**
 * Created by Jungle on 2018/10/17 0017.
 * @desc TODO
 */
fun Context.toast(message: String, lenght: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, lenght).show()
}