package com.akkalomator.libcommon.storage

object Storage {

    var storage: LocalStorage? = null

    interface LocalStorage {
        var sid: String?
    }
}