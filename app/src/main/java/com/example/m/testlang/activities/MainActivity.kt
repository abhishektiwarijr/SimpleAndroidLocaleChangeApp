package com.example.m.testlang.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.AppCompatSpinner
import android.view.Menu
import android.view.MenuItem
import com.example.m.testlang.R
import com.example.m.testlang.fragments.TestFragment
import com.example.m.testlang.utils.LanguageSetting.LANGUAGE_ENGLISH
import com.example.m.testlang.utils.LanguageSetting.LANGUAGE_HINDI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        var onceClicked:Boolean=true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        tv_hello.setOnClickListener {
            if(onceClicked) {
                supportFragmentManager.beginTransaction().add(R.id.fl_hello, TestFragment()).commit()
                onceClicked =false
            } else {
                startActivity(Intent(this, SecondActivity::class.java))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
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

    private fun showChangeLangDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.language_dialog,null)
        dialogBuilder.setView(dialogView)

        val spinner1:AppCompatSpinner = dialogView!!.findViewById(R.id.spinner1)

        dialogBuilder.setTitle(resources.getString(R.string.lang_dialog_title))
        dialogBuilder.setPositiveButton(getString(R.string.change), DialogInterface.OnClickListener { _, _ ->
            val langPos = spinner1.selectedItemPosition
            when (langPos) {
                0 //English
                -> {
                    setLanguage(LANGUAGE_ENGLISH)
                    return@OnClickListener
                }
                1 //Hindi
                -> {
                    setLanguage(LANGUAGE_HINDI)
                    return@OnClickListener
                }
                else //By default set to english
                -> {
                    setLanguage(LANGUAGE_ENGLISH)
                    return@OnClickListener
                }
            }
        })
        dialogBuilder.setNegativeButton(getString(R.string.cancel)) { _, _ ->
            //TODO
        }
        val b = dialogBuilder.create()
        b.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onceClicked=true
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
