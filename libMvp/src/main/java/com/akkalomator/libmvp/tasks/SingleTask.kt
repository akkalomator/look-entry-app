package com.akkalomator.libmvp.tasks

import android.util.Log
import com.akkalomator.libmvp.tasks.cancellationTokens.DisposableCancellationToken
import io.reactivex.Single

class SingleTask<T>(
    override val key: Any,
    private val single: Single<T>,
    private val onSuccess: ((T) -> Unit)? = null,
    private val onError: ((Throwable) -> Unit)? = null
) : Task(key) {

    override fun execute(onFinished: () -> Unit): DisposableCancellationToken {
        return DisposableCancellationToken(
            single.subscribe(
                {
                    onSuccess?.invoke(it)
                    onFinished.invoke()
                },
                {
                    Log.e(this.toString(), "FAILED TASK", it)
                    onError?.invoke(it)
                    onFinished.invoke()
                }
            )
        )
    }

}