package com.example.m.testlang.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.util.DisplayMetrics;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Abhishek Tiwari on 22.09.2017.
 **/
public class LocalizationAgent {
    private static final String KEY_ACTIVITY_LOCALE_CHANGED = "activity_locale_changed";
    // Boolean flag to check that activity was recreated from locale changed.
    private boolean isLocalizationChanged = false;
    // Prepare default language.
    private String currentLanguage = LanguageSetting.getDefaultLanguage();
    private final Activity activity;
    private final List<OnLocaleChangedListener> localeChangedListeners = new ArrayList<>();

    public LocalizationAgent(Activity activity) {
        this.activity = activity;
    }

    public void addOnLocaleChangedListener(OnLocaleChangedListener onLocaleChangedListener) {
        localeChangedListeners.add(onLocaleChangedListener);
    }

    public void onCreate() {
        setupLanguage();
        checkBeforeLocaleChanging();
    }

    // Provide method to set application language by country name.
    public final void setLanguage(String language) {
        if (isCurrentLanguageSetting(language)) {
            LanguageSetting.setLanguage(activity, language);
            notifyLanguageChanged();
        }
    }

    // Provide method to set application language by locale.
    public final void setLanguage(Locale locale) {
        setLanguage(locale.getLanguage());
    }

    public final void setDefaultLanguage(String language) {
        LanguageSetting.setDefaultLanguage(language);
    }

    public final void setDefaultLanguage(Locale locale) {
        LanguageSetting.setDefaultLanguage(locale.getLanguage());
    }

    // Get current language
    public final String getLanguage() {
        return LanguageSetting.getLanguage();
    }

    // Get current locale
    public final Locale getLocale() {
        return LanguageSetting.getLocale(activity);
    }

    // Check that bundle come from locale change.
    // If yes, bundle will obe remove and set boolean flag to "true".
    private void checkBeforeLocaleChanging() {
        boolean isLocalizationChanged = activity.getIntent().getBooleanExtra(KEY_ACTIVITY_LOCALE_CHANGED, false);
        if (isLocalizationChanged) {
            this.isLocalizationChanged = true;
            activity.getIntent().removeExtra(KEY_ACTIVITY_LOCALE_CHANGED);
        }
    }

    // Setup language to locale and language preference.
    // This method will called before onCreate.
    private void setupLanguage() {
        Locale locale = LanguageSetting.getLocale(activity);
        setupLocale(locale);
        currentLanguage = locale.getLanguage();
        LanguageSetting.setLanguage(activity, locale.getLanguage());
    }

    // Set locale configuration.
    private void setupLocale(Locale locale) {
        updateLocaleConfiguration(activity, locale);
    }

    private void updateLocaleConfiguration(Context context, Locale locale) {
        Configuration config = context.getResources().getConfiguration();
        config.locale = locale;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        context.getResources().updateConfiguration(config, dm);
    }

    // Avoid duplicated setup
    private boolean isCurrentLanguageSetting(String language) {
        return !language.equals(LanguageSetting.getLanguage());
    }

    // Let's take it change! (Using recreate method that available on API 11 or more.
    private void notifyLanguageChanged() {
        sendOnBeforeLocaleChangedEvent();
        activity.getIntent().putExtra(KEY_ACTIVITY_LOCALE_CHANGED, true);
        callDummyActivity();
        activity.recreate();
    }

    // If activity is run to backStack. So we have to check if this activity is resume working.
    public void onResume() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                checkLocaleChange();
                checkAfterLocaleChanging();
            }
        });
    }

    // Check if locale has change while this activity was run to backStack.
    private void checkLocaleChange() {
        if (isCurrentLanguageSetting(currentLanguage)) {
            sendOnBeforeLocaleChangedEvent();
            isLocalizationChanged = true;
            callDummyActivity();
            activity.recreate();
        }
    }

    // Call override method if local is really changed
    private void checkAfterLocaleChanging() {
        if (isLocalizationChanged) {
            sendOnAfterLocaleChangedEvent();
            isLocalizationChanged = false;
        }
    }

    private void sendOnBeforeLocaleChangedEvent() {
        for (OnLocaleChangedListener changedListener : localeChangedListeners) {
            changedListener.beforeLocaleChanged();
        }
    }

    private void sendOnAfterLocaleChangedEvent() {
        for (OnLocaleChangedListener listener : localeChangedListeners) {
            listener.afterLocaleChanged();
        }
    }

    private void callDummyActivity() {
        activity.startActivity(new Intent(activity, FlashActivity.class));
    }
}