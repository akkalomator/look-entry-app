package com.akkalomator.lookentryapp.screens.main

import com.akkalomator.lookentryapp.mvp.MvpActivity

class MainActivity : MvpActivity<MainActivityPresenter, MainActivityView>() {
    override fun createPresenter() = MainActivityPresenter()

    override fun createView() = MainActivityView(this)
}