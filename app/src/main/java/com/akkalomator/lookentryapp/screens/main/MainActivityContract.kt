package com.akkalomator.lookentryapp.screens.main

import com.akkalomator.libmvp.CanAttachDetach

object MainActivityContract {

    interface MainActivityPresenter : CanAttachDetach {
        fun showProfile()
        fun removeCurrentProfile()
    }

    interface MainActivityView : CanAttachDetach {
        fun renderState(state: State)
    }

    sealed class State {

        data class Ping(
            val status: PingStatus
        ) : State() {
            enum class PingStatus {
                ONGOING,
                SUCCESS,
                FAIL
            }
        }

        data class Code(
            val code: Int
        ) : State()

        data class Ready(val code: Int?) : State()
    }


}