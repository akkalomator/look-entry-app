package com.akkalomator.libmvp.tasks.cancellationTokens

interface CancellationToken {
    var cancelled: Boolean

    fun cancel()
}