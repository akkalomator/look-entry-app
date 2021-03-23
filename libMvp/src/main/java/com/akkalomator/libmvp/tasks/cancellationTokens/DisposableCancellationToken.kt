package com.akkalomator.libmvp.tasks.cancellationTokens

import io.reactivex.disposables.Disposable

class DisposableCancellationToken(
    private val subscription: Disposable
) : CancellationToken {
    override var cancelled = false

    override fun cancel() {
        if (!subscription.isDisposed && !cancelled) {
            cancelled = true
            subscription.dispose()
        }
    }
}