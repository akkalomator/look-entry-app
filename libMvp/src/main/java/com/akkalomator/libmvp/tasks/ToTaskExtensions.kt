package com.akkalomator.libmvp.tasks

import io.reactivex.Single

fun<T> Single<T>.toTask(
    onSuccess: ((T) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null
) = SingleTask(
    this,
    this,
    onSuccess,
    onError
)

fun<T> (() -> T).toTask(
    onResult: ((T) -> Unit)? = null
) = RunnableTask(
    this,
    this,
    onResult
)