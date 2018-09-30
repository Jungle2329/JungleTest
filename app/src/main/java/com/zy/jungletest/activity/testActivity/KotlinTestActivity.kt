package com.zy.jungletest.activity.testActivity

import android.content.ComponentName
import android.content.Intent
import com.zy.jungletest.R
import com.zy.jungletest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_retrofit.*

/**
 * Created by Jungle on 2018/7/24 0024.
 * @desc TODO
 */
class KotlinTestActivity : BaseActivity() {

    override fun getViewId(): Int {
        return R.layout.activity_retrofit
    }

    override fun initView() {
        val a = getData("323")
        a.compareTo(Byte.MAX_VALUE)
    }

    override fun initData() {

    }

    override fun initListener() {
        fab.setOnClickListener {
            val name = ComponentName("com.etwod.wpxia","com.etwod.wpxia.ui.index.activity.IndexActivity")
            val intent = Intent()
            intent.component = name
            startActivity(intent)
        }

    }

    private fun getData(str: String): Int {
        return Integer.parseInt(str)
    }

}