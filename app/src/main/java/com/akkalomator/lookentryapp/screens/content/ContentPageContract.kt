package com.akkalomator.lookentryapp.screens.content

class ContentPageContract {

    interface Presenter

    interface View {
        fun renderState(state: State)

        fun showSidError()
    }

    sealed class State {
        object Loading : State()

        data class Content(
            val title: String
        ) : State()
    }
}