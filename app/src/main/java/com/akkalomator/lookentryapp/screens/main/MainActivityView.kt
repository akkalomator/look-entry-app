package com.akkalomator.lookentryapp.screens.main

import android.util.Log
import com.akkalomator.lookentryapp.mvp.MvpView
import com.akkalomator.lookentryapp.R
import com.akkalomator.lookentryapp.databinding.ActivityMainBinding
import com.akkalomator.lookentryapp.mvp.MvpActivity
import com.akkalomator.lookentryapp.utils.visible

class MainActivityView(activity: MvpActivity<*, *>) :
    MvpView<MainActivityPresenter>(activity),
    MainActivityContract.MainActivityView {

    private val binding = ActivityMainBinding.inflate(activity.layoutInflater)
        .also {
            activity.setContentView(it.root)
        }

    override fun onAttached() {
        super.onAttached()

        binding.btnRemoveCurrentProfile.setOnClickListener {
            presenter?.removeCurrentProfile()
        }

        binding.btnCodeUsed.setOnClickListener {
            presenter?.showProfile()
        }
    }

    override fun renderState(state: MainActivityContract.State) {
        when (state) {
            is MainActivityContract.State.Ping -> {
                binding.codeLayout.visible = false
                binding.status.visible = true
                binding.btnCodeUsed.visible = false
                binding.btnRemoveCurrentProfile.visible = false

                binding.status.text = when (state.status) {
                    MainActivityContract.State.Ping.PingStatus.ONGOING ->
                        context.resources.getString(R.string.ping_ongoing)
                    MainActivityContract.State.Ping.PingStatus.FAIL ->
                        context.resources.getString(R.string.ping_failed)
                    MainActivityContract.State.Ping.PingStatus.SUCCESS ->
                        context.resources.getString(R.string.ping_successful)
                }
            }

            is MainActivityContract.State.Code -> {
                binding.codeLayout.visible = true
                binding.status.visible = false
                binding.btnCodeUsed.visible = true
                binding.btnRemoveCurrentProfile.visible = false

                binding.tvCode.text = state.code.toString()
            }
            is MainActivityContract.State.Ready -> {
                binding.codeLayout.visible = false
                binding.status.visible = false
                binding.btnCodeUsed.visible = true
                binding.btnRemoveCurrentProfile.visible = true

                state.code?.let {
                    binding.codeLayout.visible = true
                    binding.tvCode.text = it.toString()
                }
            }
        }
    }
}