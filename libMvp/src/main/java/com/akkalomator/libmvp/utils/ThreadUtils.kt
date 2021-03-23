package com.akkalomator.libmvp.utils

import android.os.Handler
import android.os.Looper

object ThreadUtils {

    private val sUiThreadHandler = Handler(Looper.getMainLooper())

    fun runOnUiThread(r: Runnable) {
        if (runningOnUiThread()) {
            r.run()
        } else {
            sUiThreadHandler.post(r)
        }
    }

    fun runningOnUiThread(): Boolean {
        return Looper.getMainLooper() == Looper.myLooper()
    }
}