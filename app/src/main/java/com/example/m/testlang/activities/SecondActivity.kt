package com.example.m.testlang.activities

import android.content.Intent
import android.os.Bundle
import com.example.m.testlang.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        tv_hello.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}