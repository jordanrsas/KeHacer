package com.jordanrosas.kehacer.domain.interactor

import io.reactivex.disposables.Disposable

abstract class UseCase<Params, R : Any> {
    private var disposable: Disposable? = null

    /**
     * @param params Params?
     * @return R
     */
    protected abstract fun buildUseCase(params: Params? = null): R

    /**
     *
     */
    fun dispose() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    /**
     *
     * @param subscription Disposable?
     */
    protected fun subscribe(subscription: Disposable? = null) {
        disposable = subscription
    }
}