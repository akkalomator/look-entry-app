package com.akkalomator.libmvp

import android.util.Log

object PresentersManager {

    private val presenters: MutableMap<String, MvpPresenter<*>> = mutableMapOf()

    private val monitor = Any()

    fun<TPresenter : MvpPresenter<TView>, TView : CanAttachDetach> getPresenter(key: String, createPresenter: () -> TPresenter) : TPresenter {
        synchronized(monitor) {
            Log.e(this.toString(), "key=$key task2")
            var presenter = presenters[key] as? TPresenter
            if (presenter == null) {
                presenter = createPresenter.invoke()
                presenters[key] = presenter
            }
            return presenter
        }
    }

    fun destroyPresenter(key: String) {
        presenters.remove(key)
    }
}