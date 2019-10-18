package com.zy.jungletest.ui.activity.testActivity

import com.zy.jungletest.R
import com.zy.jungletest.api.toast
import com.zy.jungletest.base.BaseActivity

/**
 * Created by Jungle on 2018/8/8 0008.
 * @desc TODO
 */
class NewTestActivity : BaseActivity() {

    override fun getViewId(): Int {
        return R.layout.activity_new_test
    }

    override fun initView() {
        toast("12")
    }

    override fun initData() {
    }

    override fun initListener() {

    }


}

