package com.zy.jungletest.ui.activity.testActivity

import android.os.Build
import com.zy.commonlibrary.utils.TTSUtils
import com.zy.jungletest.R
import com.zy.jungletest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_makesounds.*

/**
 * Created by Jungle on 2018/11/12 0012.
 * @desc tts
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
        btn_play.setOnClickListener {
            TTSUtils.Builder()
                    .with(this)
                    .setText(et_input.text.toString().trim())
                    .build()
                    .play()
        }
        btn_confirm.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                TTSUtils.Builder()
                        .with(this)
                        .setText(et_input.text.toString().trim())
                        .build()
                        .saveFile()
            }
        }
    }
}