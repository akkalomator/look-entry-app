package com.akkalomator.libcommon.items

sealed class CodeResponse {
    data class Success(
        val sid: String
    ) : CodeResponse()

    data class CodeAccepted(
        val sid: String
    ) : CodeResponse()

    object CodeInUse : CodeResponse()

    object Fail : CodeResponse()
}