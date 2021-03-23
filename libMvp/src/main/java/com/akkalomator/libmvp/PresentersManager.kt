package com.akkalomator.libmvp

import kotlin.reflect.full.memberFunctions

object PresentersManager {

    private val presenters: MutableMap<String, MvpPresenter<*>> = mutableMapOf()

    private val monitor = Any()

    fun<TPresenter : MvpPresenter<TView>, TView : CanAttachDetach> getPresenter(createPresenter: () -> TPresenter) : TPresenter {
        synchronized(monitor) {
            val key = getKey(createPresenter)
            var presenter = presenters[key] as? TPresenter
            if (presenter == null) {
                presenter = createPresenter.invoke()
                presenters[key] = presenter
            }
            return presenter
        }
    }

    private fun getKey(createPresenter: () -> MvpPresenter<*>) =
        createPresenter::class.java.declaredMethods[0].returnType.name

    fun destroyPresenter(presenter: MvpPresenter<CanAttachDetach>) {
        val key = presenter.javaClass.name
        presenters[key]?.onDetached()
        presenters.remove(key)
    }
}