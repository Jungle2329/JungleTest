package com.zy.jungletest.ui.start

import android.animation.Animator
import android.content.Intent
import android.util.Log
import com.zy.jungletest.R
import com.zy.jungletest.activity.MainActivity
import com.zy.jungletest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by Jungle on 2018/11/30 0030.
 * @desc Splash
 */
class SplashActivity : BaseActivity() {

    override fun getViewId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
    }

    override fun initData() {

    }

    override fun initListener() {
        val mListener = LAVAnimListener()
        lav_logo.addAnimatorListener(mListener)
        tv_ship.setOnClickListener {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
            lav_logo.removeAnimatorListener(mListener)
        }
    }

    inner class LAVAnimListener : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
            Log.i("zhangyi", "onAnimationRepeat")
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }

        override fun onAnimationEnd(animation: Animator?) {
            Log.i("zhangyi", "onAnimationEnd")
        }

        override fun onAnimationCancel(animation: Animator?) {
            Log.i("zhangyi", "onAnimationCancel")
        }

        override fun onAnimationStart(animation: Animator?) {
            Log.i("zhangyi", "onAnimationStart")
        }
    }
}