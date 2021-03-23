package com.akkalomator.lookentryapp.screens.content

import android.app.Activity
import android.widget.Toast
import com.akkalomator.lookentryapp.R
import com.akkalomator.lookentryapp.databinding.ActivityContentBinding
import com.akkalomator.lookentryapp.mvp.MvpView

class ContentView(activity: Activity) : MvpView<ContentPresenter>(activity), ContentPageContract.View {

    private val binding = ActivityContentBinding.inflate(activity.layoutInflater)
        .also {
            activity.setContentView(it.root)
        }

    override fun renderState(state: ContentPageContract.State) {
        when (state) {
            is ContentPageContract.State.Loading -> binding.tvTitle.setText(R.string.loading)
            is ContentPageContract.State.Content -> binding.tvTitle.text = state.title
        }
    }

    override fun showSidError() {
        Toast.makeText(context, R.string.sid_error_msg, Toast.LENGTH_LONG).show()
    }
}