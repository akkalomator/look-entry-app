package com.akkalomator.libmvp.utils

import android.os.Handler
import android.os.Looper
import com.akkalomator.libmvp.tasks.cancellationTokens.DisposableCancellationToken
import com.akkalomator.libmvp.tasks.Task
import com.akkalomator.libmvp.tasks.cancellationTokens.CancellationToken

class Executor {

    private val monitor = Any()

    private val tokens = HashMap<Any, CancellationToken>()

    fun execute(task: Task) {
        synchronized(monitor) {
            tokens[task.key] = task.execute {
                tokens.remove(task.key)
            }
        }
    }

    fun executeDelayed(delay: Long, task: Task) {
        val looper = Looper.myLooper() ?: Looper.getMainLooper()
        Handler(looper).postDelayed(
            {
                execute(task)
            },
            delay
        )
    }

    fun cancelAll() {
        tokens.values.forEach { it.cancel() }
    }
}