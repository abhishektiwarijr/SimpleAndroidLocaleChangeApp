package com.example.m.testlang.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.m.testlang.R
import com.example.m.testlang.fragments.TestFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        var onceClicked: Boolean = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        tv_hello.setOnClickListener {
            if (onceClicked) {
                supportFragmentManager.beginTransaction().add(R.id.fl_hello, TestFragment()).commit()
                onceClicked = false
            } else {
                startActivity(Intent(this, SecondActivity::class.java))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.action_change_language -> {
                showChangeLangDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onceClicked = true
    }
}