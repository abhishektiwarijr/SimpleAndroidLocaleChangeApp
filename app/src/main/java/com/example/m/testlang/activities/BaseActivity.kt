package com.example.m.testlang.activities

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import com.example.m.testlang.R
import com.example.m.testlang.utils.LanguageSetting
import com.example.m.testlang.utils.LocalizationAgent
import com.example.m.testlang.utils.OnLocaleChangedListener
import java.util.*

abstract class BaseActivity : AppCompatActivity(), OnLocaleChangedListener {
    private var localizationAgent: LocalizationAgent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        localizationAgent = LocalizationAgent(this)
        localizationAgent?.addOnLocaleChangedListener(this)
        localizationAgent?.onCreate()
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        localizationAgent?.onResume()
    }

    fun setLanguage(language: String) {
        localizationAgent?.language = language
    }

    fun setLanguage(locale: Locale) {
        localizationAgent?.setLanguage(locale)
    }

    fun setDefaultLanguage(language: String) {
        localizationAgent?.setDefaultLanguage(language)
    }

    fun setDefaultLanguage(locale: Locale) {
        localizationAgent?.setDefaultLanguage(locale)
    }

    fun getLanguage(): String {
        return localizationAgent!!.language
    }

    fun getLocale(): Locale {
        return localizationAgent!!.locale
    }

    override fun beforeLocaleChanged() = Unit
    override fun afterLocaleChanged() = Unit

    fun showChangeLangDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.language_dialog, null)
        dialogBuilder.setView(dialogView)

        val spinner1: AppCompatSpinner = dialogView!!.findViewById(R.id.spinner1)

        dialogBuilder.setTitle(resources.getString(R.string.lang_dialog_title))
        dialogBuilder.setPositiveButton(getString(R.string.change), DialogInterface.OnClickListener { _, _ ->
            val langPos = spinner1.selectedItemPosition
            when (langPos) {
                1 //Hindi
                -> {
                    setLanguage(LanguageSetting.LANGUAGE_HINDI)
                    return@OnClickListener
                }
                else //By default set to english
                -> {
                    setLanguage(LanguageSetting.LANGUAGE_ENGLISH)
                    return@OnClickListener
                }
            }
        })
        dialogBuilder.setNegativeButton(getString(R.string.cancel)) { _, _ ->

        }
        val b = dialogBuilder.create()
        b.show()
    }
}