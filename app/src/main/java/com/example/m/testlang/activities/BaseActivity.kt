package com.example.m.testlang.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.m.testlang.utils.LocalizationAgent
import com.example.m.testlang.utils.OnLocaleChangedListener
import java.util.*

abstract class BaseActivity : AppCompatActivity(),OnLocaleChangedListener {
    private var localizationAgent: LocalizationAgent?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        localizationAgent = LocalizationAgent(this)
        localizationAgent!!.addOnLocaleChangedListener(this)
        localizationAgent!!.onCreate()
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        localizationAgent!!.onResume()
    }

    fun setLanguage(language: String) {
        localizationAgent!!.language = language
    }

    fun setLanguage(locale: Locale) {
        localizationAgent!!.setLanguage(locale)
    }

    fun setDefaultLanguage(language: String) {
        localizationAgent!!.setDefaultLanguage(language)
    }

    fun setDefaultLanguage(locale: Locale) {
        localizationAgent!!.setDefaultLanguage(locale)
    }

    fun getLanguage(): String {
        return localizationAgent!!.language
    }

    fun getLocale(): Locale {
        return localizationAgent!!.locale
    }

    override fun beforeLocaleChanged() {}

    override fun afterLocaleChanged() {}
}