package com.akkalomator.libmvp

import android.util.Log
import com.akkalomator.libmvp.tasks.Task
import com.akkalomator.libmvp.tasks.toTask
import com.akkalomator.libmvp.utils.Executor
import com.akkalomator.libmvp.utils.ThreadUtils

abstract class MvpPresenter<TView: CanAttachDetach> : CanAttachDetach {

    protected var view: TView? = null

    private val taskHandlerWhileViewAttached = Executor()
    private val taskHandlerWhilePresenterAlive = Executor()

    fun attachView(view: TView) {
        this.view = view
        this.view?.onAttached()
        onViewAttached()
    }

    fun detachView() {
        taskHandlerWhileViewAttached.cancelAll()
        view?.onDetached()
        view = null
        onViewDetached()
    }

    fun withView(block: TView.() -> Unit) {
        ThreadUtils.runOnUiThread {
            view?.block()
        }
    }

    fun runWhileViewAttached(task: Task) {
        taskHandlerWhileViewAttached.execute(task)
    }

    fun runWhilePresenterAlive(task: Task) {
        taskHandlerWhilePresenterAlive.execute(task)
    }

    fun runWhilePresenterAlive(block: () -> Unit) {
        runWhilePresenterAlive(block.toTask())
    }

    fun executeDelayedWhileViewAttached(delay: Long, task: Task) {
        taskHandlerWhileViewAttached.executeDelayed(delay, task)
    }

    fun executeDelayedWhileViewAttached(delay: Long, block: () -> Unit) {
        executeDelayedWhileViewAttached(delay, block.toTask())
    }

    fun executeDelayedWhilePresenterAlive(delay: Long, task: Task) {
        taskHandlerWhilePresenterAlive.executeDelayed(delay, task)
    }

    fun executeDelayedWhilePresenterAlive(delay: Long, block: () -> Unit) {
        executeDelayedWhileViewAttached(delay, block.toTask())
    }

    protected open fun onViewAttached() {}

    protected open fun onViewDetached() {
        taskHandlerWhileViewAttached.cancelAll()
    }

    override fun onAttached() {
        Log.d(this.toString(), "OnPresenterAttached")
    }
    override fun onDetached() {
        Log.d(this.toString(), "OnPresenterAttached")
        taskHandlerWhileViewAttached.cancelAll()
        taskHandlerWhilePresenterAlive.cancelAll()
    }
}