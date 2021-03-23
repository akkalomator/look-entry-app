package com.akkalomator.lookentryapp.screens.content

import com.akkalomator.libcommon.api.Api
import com.akkalomator.libcommon.items.Profile
import com.akkalomator.libmvp.MvpPresenter
import com.akkalomator.libmvp.tasks.toTask

class ContentPresenter(private val sid: String?) : MvpPresenter<ContentView>(),
    ContentPageContract.Presenter {

    override fun onViewAttached() {
        super.onViewAttached()

        if (sid == null) {
            withView {
                showSidError()
                router.gotoMainPage()
            }
            return
        }

        runWhilePresenterAlive(
            Api().getProfile(sid)
                .toTask(
                    {
                        renderState(it)
                    },
                    {
                        withView {
                            showSidError()
                            router.gotoMainPage()
                        }
                    }
                )
        )

        renderState()
    }

    private fun renderState(profile: Profile? = null) {
        val state = if (profile == null)
            ContentPageContract.State.Loading
        else
            ContentPageContract.State.Content(profile.title)

        withView {
            renderState(state)
        }
    }

}