package com.lagos.mycv.interactors

import com.lagos.mycv.api.ApiGistInterface
import com.lagos.mycv.models.ProfileModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainInteractor {
    var disposable: Disposable? = null
    private val mApiService by lazy {
        ApiGistInterface.create()
    }

    interface OnFinishedListener {
        fun onResultSuccess(profile: ProfileModel)
        fun onResultFail()
    }

    fun requestGetDataAPI(onFinishedListener: OnFinishedListener) {
        // Get data from server
        disposable = mApiService.getProfileData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result: ProfileModel -> onFinishedListener.onResultSuccess(result) },
                { error -> onFinishedListener.onResultFail() }
            )
    }
}