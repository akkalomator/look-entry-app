package com.akkalomator.lookentryapp.screens.content

import com.akkalomator.lookentryapp.mvp.MvpActivity
import com.akkalomator.lookentryapp.mvp.Router

class ContentActivity : MvpActivity<ContentPresenter, ContentView>() {

    private var sid: String? = null

    override fun createPresenter(): ContentPresenter {
        sid = intent.extras?.getString(Router.PageParams.ContentPage.SID)
        return ContentPresenter(
            sid
        )
    }

    override fun createView() = ContentView(this)
}