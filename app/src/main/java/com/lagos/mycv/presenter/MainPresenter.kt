package com.lagos.mycv.presenter

import com.lagos.mycv.interactors.MainInteractor
import com.lagos.mycv.models.ProfileModel
import com.lagos.mycv.view.MainView

class MainPresenter(private var mainView: MainView?, private val mainInteractor: MainInteractor) : MainInteractor.OnFinishedListener {

    fun getData() {
        mainView?.showProgress()
        mainInteractor.requestGetDataAPI(this)
    }

    override fun onResultSuccess(profile: ProfileModel) {
        mainView?.hideProgress()
        mainView?.setData(profile)
    }

    override fun onResultFail() {
        mainView?.hideProgress()
        mainView?.setDataError()
    }

    fun onPause() {
        mainInteractor.disposable?.dispose()
        mainView = null
    }
}