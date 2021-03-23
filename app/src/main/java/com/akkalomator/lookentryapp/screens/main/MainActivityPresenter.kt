package com.akkalomator.lookentryapp.screens.main

import com.akkalomator.libmvp.MvpPresenter
import com.akkalomator.libcommon.storage.Storage
import com.akkalomator.libcommon.api.Api
import com.akkalomator.libcommon.items.CodeResponse
import com.akkalomator.libcommon.items.PingResponse
import com.akkalomator.libmvp.tasks.toTask
import kotlin.random.Random

class MainActivityPresenter: MvpPresenter<MainActivityView>(), MainActivityContract.MainActivityPresenter {

    private var pingStatus = MainActivityContract.State.Ping.PingStatus.ONGOING
    private var sid: String? = null
    private var code: Int? = null

    override fun onViewAttached() {
        runWhilePresenterAlive(
            Api().ping().toTask(
                onSuccess = {
                    pingStatus = when (it.status) {
                        PingResponse.Status.SUCCESS -> {
                            executeDelayedWhileViewAttached(500L) {
                                checkSid()
                            }
                            MainActivityContract.State.Ping.PingStatus.SUCCESS
                        }
                        PingResponse.Status.ERROR -> MainActivityContract.State.Ping.PingStatus.FAIL
                    }
                    renderState()
                },
                onError = {
                    pingStatus = MainActivityContract.State.Ping.PingStatus.FAIL
                    renderState()
                }
            )
        )
        renderState()
    }

    private fun checkSid() {
        val sid = Storage.storage?.sid
        if (sid == null) {
            generateCode()
        } else {
            this.sid = sid
            renderState()
        }
    }

    private fun generateCode() {
        val code = Random.nextInt(1000000)
        this.code = code

        runWhilePresenterAlive {
            Api().sendCode(code)
                .subscribe(
                    {
                        when (it) {
                            is CodeResponse.Fail -> {
                                executeDelayedWhilePresenterAlive(10000L) {
                                    generateCode()
                                }
                            }
                            is CodeResponse.Success -> {
                                sid = it.sid
                                saveSid(it.sid)
                                renderState()
                            }
                        }
                    },
                    {
                        executeDelayedWhilePresenterAlive(10000L) {
                            generateCode()
                        }
                    }
                )
        }

        renderState()
    }

    private fun saveSid(sid: String) {
        Storage.storage?.sid = sid
    }

    private fun renderState() {
        withView {
            val code = code
            val state = when {
                pingStatus != MainActivityContract.State.Ping.PingStatus.SUCCESS -> MainActivityContract.State.Ping(pingStatus)
                sid != null -> MainActivityContract.State.Ready(code)
                code != null -> MainActivityContract.State.Code(code)
                else -> MainActivityContract.State.Ping(pingStatus)
            }
            renderState(
                state
            )
        }
    }

    override fun showProfile() {
        withView {
            sid?.let {
                router.gotoContentPage(it)
            }
        }
    }

    override fun removeCurrentProfile() {
        sid = null
        Storage.storage?.sid = null
        generateCode()
    }
}