package com.example.m.testlang.activities

import android.os.Bundle
import android.widget.Toast
import com.example.m.testlang.R
import com.example.m.testlang.fragments.FirstFragment
import kotlinx.android.synthetic.main.activity_main.*

class ThirdActivity : BaseActivity() {

    private var onceClicked: Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        tv_hello.setOnClickListener {
            if(onceClicked) {
                supportFragmentManager.beginTransaction().add(R.id.fl_hello, FirstFragment()).commit()
                onceClicked=false
            } else {
                Toast.makeText(this,getString(R.string.dead_end_ahead),Toast.LENGTH_SHORT).show()
            }
        }
    }
}