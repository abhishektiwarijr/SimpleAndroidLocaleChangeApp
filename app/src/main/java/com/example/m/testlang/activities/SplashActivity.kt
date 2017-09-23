package com.example.m.testlang.activities

import android.content.Intent
import android.os.Bundle
import com.example.m.testlang.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        tv_splash.setText(R.string.welcome)
        tv_splash.setOnClickListener {
            startActivity(Intent(this, FourthActivity::class.java))
        }
    }
}