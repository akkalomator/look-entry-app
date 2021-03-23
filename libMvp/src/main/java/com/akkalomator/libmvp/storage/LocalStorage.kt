package com.akkalomator.libmvp.storage

object Storage {

    var storage: LocalStorage? = null

    interface LocalStorage {
        var sid: String?
    }
}