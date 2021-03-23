package com.akkalomator.libmvp.tasks

import com.akkalomator.libmvp.tasks.cancellationTokens.CouroutineCancellationToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RunnableTask<T>(
    key: Any,
    private val block: () -> T,
    private val onResult: ((T) -> Unit)? = null
) : Task(key) {

    override fun execute(onFinished: () -> Unit): CouroutineCancellationToken {
        return CouroutineCancellationToken(GlobalScope.launch {
            val result = block.invoke()
            onResult?.invoke(result)
            onFinished.invoke()
        })
    }
}