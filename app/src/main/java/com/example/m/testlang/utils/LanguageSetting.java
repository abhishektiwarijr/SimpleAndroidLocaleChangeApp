package com.example.m.testlang.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Locale;

/**
 * Created by Abhishek Tiwari on 22.09.2017.
 **/
public class LanguageSetting {
    public static final  String LANGUAGE_ENGLISH = "en";
    public static final  String LANGUAGE_HINDI= "hi";
    private static final String PREFERENCE_LANGUAGE = "pref_language";
    private static final String KEY_LANGUAGE = "key_language";
    private static String DEFAULT_LANGUAGE = Locale.ENGLISH.getLanguage();
    private static String currentLanguage = Locale.ENGLISH.getLanguage();

    static void setDefaultLanguage(String language) {
        DEFAULT_LANGUAGE = language;
    }

    static String getDefaultLanguage() {
        return DEFAULT_LANGUAGE;
    }

    static void setLanguage(Context context, String language) {
        currentLanguage = language;
        SharedPreferences.Editor editor = getLanguagePreference(context).edit();
        editor.putString(KEY_LANGUAGE, language);
        editor.apply();
    }

    public static void setLanguage(Context context, Locale locale) {
        setLanguage(context, locale.getLanguage());
    }

    static String getLanguage() {
        return currentLanguage;
    }

    private static String getLanguage(Context context) {
        return getLanguagePreference(context).getString(KEY_LANGUAGE, DEFAULT_LANGUAGE);
    }

    private static SharedPreferences getLanguagePreference(Context context) {
        return context.getSharedPreferences(PREFERENCE_LANGUAGE, Activity.MODE_PRIVATE);
    }

    public static void setDefaultLanguage(Locale locale) {
        LanguageSetting.setDefaultLanguage(locale.getLanguage());
    }

    public static Locale getLocale() {
        return getLocale(getLanguage());
    }

    static Locale getLocale(Context context) {
        return getLocale(getLanguage(context));
    }

    private static Locale getLocale(String language) {
        return new Locale(language.toLowerCase(Locale.getDefault()));
    }
}