package com.akkalomator.libcommon.items

data class PingResponse(
    val status: Status
) {

    enum class Status {
        ERROR,
        SUCCESS
    }
}
