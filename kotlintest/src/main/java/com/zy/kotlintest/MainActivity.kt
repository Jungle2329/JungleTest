package com.zy.kotlintest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById(R.id.tv_01).setOnClickListener {
            val intent = Intent(this@MainActivity, KotlinTestActivity::class.java)
            startActivity(intent)

            val inite = Intent(this, KotlinTestActivity::class.java)
            startActivity(inite)
        }

    }
}
