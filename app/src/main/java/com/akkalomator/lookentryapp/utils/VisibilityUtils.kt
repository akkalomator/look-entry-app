package com.akkalomator.lookentryapp.utils

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.visible(visible: Boolean) {
    visibility = if (visible) VISIBLE else GONE
}

var View.visible: Boolean
    get() = visibility == VISIBLE
    set(value) = visible(value)