package com.zy.jungletest.activity.testActivity

import com.zy.commonlibrary.utils.TTSUtils
import com.zy.jungletest.R
import com.zy.jungletest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_makesounds.*

/**
 * Created by Jungle on 2018/11/12 0012.
 * @desc
 */
class MakeSoundsActivity : BaseActivity() {

    override fun getViewId(): Int {
        return R.layout.activity_makesounds
    }

    override fun initView() {
    }

    override fun initData() {

    }

    override fun initListener() {
        btn_confirm.setOnClickListener {
            TTSUtils.getInstance().saveFile(et_input.text.toString().trim())
        }
    }
}