package com.akkalomator.libmvp.tasks

import com.akkalomator.libmvp.tasks.cancellationTokens.CancellationToken

abstract class Task(open val key: Any) {

    abstract fun execute(onFinished: () -> Unit) : CancellationToken
}