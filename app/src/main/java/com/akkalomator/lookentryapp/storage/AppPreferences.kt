package com.akkalomator.lookentryapp.storage

import android.content.SharedPreferences
import com.akkalomator.libcommon.storage.Storage

object AppPreferences : Storage.LocalStorage {

    private const val SID_KEY = "SID_KEY"

    var sharedPreferences: SharedPreferences? = null

    override var sid: String?
        get() {
            return sharedPreferences?.getString(SID_KEY, null)
        }
        set(value) {
            sharedPreferences?.edit()
                ?.putString(SID_KEY, value)
                ?.apply()
        }
}