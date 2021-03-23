package com.akkalomator.lookentryapp.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akkalomator.libmvp.CanAttachDetach
import com.akkalomator.libmvp.MvpPresenter
import com.akkalomator.libmvp.PresentersManager

abstract class MvpActivity<TPresenter, TView> : AppCompatActivity()
        where TPresenter : MvpPresenter<TView>,
              TView : MvpView<TPresenter> {

    private var presenter: TPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = PresentersManager.getPresenter {
            createPresenter()
        }
        val view = createView()
        presenter?.let { presenter ->
            presenter.attachView(view)
            view.attachPresenter(presenter)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter?.onDetached()
    }

    abstract fun createPresenter(): TPresenter

    abstract fun createView(): TView
}