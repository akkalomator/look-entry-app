package com.akkalomator.libmvp.tasks.cancellationTokens

import kotlinx.coroutines.Job

class CouroutineCancellationToken(
    val job: Job
): CancellationToken {

    override var cancelled = false

    override fun cancel() {
        if (!cancelled && !job.isCancelled) {
            cancelled = true
            job.cancel()
        }
    }
}