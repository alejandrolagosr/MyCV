package com.lagos.domain.usecases

import io.reactivex.disposables.Disposable

abstract class UseCase<Params, R : Any> {

    private var disposable: Disposable? = null

    protected abstract fun buildUseCase(params: Params? = null): R

    protected fun subscribe(subscription: Disposable? = null) {
        disposable = subscription
    }

    fun dispose() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}