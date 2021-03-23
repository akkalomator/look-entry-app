package com.akkalomator.lookentryapp.mvp

import android.app.Activity
import android.content.Intent
import com.akkalomator.lookentryapp.screens.content.ContentActivity

class Router(private val activity: Activity) {

    fun gotoContentPage() {
        val intent = Intent(activity, ContentActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }
}