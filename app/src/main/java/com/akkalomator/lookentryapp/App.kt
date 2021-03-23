package com.akkalomator.lookentryapp

import android.app.Application
import com.akkalomator.libcommon.storage.Storage
import com.akkalomator.lookentryapp.storage.AppPreferences

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppPreferences.sharedPreferences = getSharedPreferences("Shared", MODE_PRIVATE);
        Storage.storage = AppPreferences
    }
}