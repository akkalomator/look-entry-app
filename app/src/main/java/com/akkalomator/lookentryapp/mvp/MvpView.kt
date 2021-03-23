package com.akkalomator.lookentryapp.mvp

import android.app.Activity
import android.content.res.Resources
import android.util.Log
import com.akkalomator.libmvp.CanAttachDetach

abstract class MvpView<TPresenter: CanAttachDetach>(
    activity: Activity
) : CanAttachDetach {


    protected val resources: Resources = activity.resources
    protected var presenter: TPresenter? = null

    val router = Router(activity)

    fun attachPresenter(presenter: TPresenter) {
        this.presenter = presenter
    }

    override fun onAttached() {
        Log.d(this.toString(), "OnViewAttached")
    }
    override fun onDetached() {
        Log.d(this.toString(), "OnViewAttached")
    }
}