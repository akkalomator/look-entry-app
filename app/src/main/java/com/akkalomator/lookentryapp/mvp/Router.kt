package com.akkalomator.lookentryapp.mvp

import android.app.Activity
import android.content.Intent
import com.akkalomator.lookentryapp.screens.content.ContentActivity
import com.akkalomator.lookentryapp.screens.main.MainActivity

class Router(private val activity: Activity) {

    fun gotoMainPage() {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    fun gotoContentPage(sid: String) {
        val intent = Intent(activity, ContentActivity::class.java)
        intent.putExtra(PageParams.ContentPage.SID, sid)
        activity.startActivity(intent)
        activity.finish()
    }

    object PageParams {
        object ContentPage {
            const val SID = "sid"
        }
    }

}