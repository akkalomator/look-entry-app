package com.akkalomator.libcommon.items

sealed class CodeResponse {
    data class Success(
        val sid: String
    ) : CodeResponse()

    object Fail : CodeResponse()
}