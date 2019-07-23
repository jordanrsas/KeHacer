package com.jordanrosas.kehacer.domain.interactor

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

/**
 *
 * @param Params
 * @property threadExecutor ThreadExecutor
 * @property postExecutionThread PostExecutionThread
 * @constructor
 */
abstract class CompletableUseCase<Params>(
    val threadExecutor: ThreadExecutor,
    val postExecutionThread: PostExecutionThread
) : UseCase<Params, Completable>() {

    /**
     *
     * @param params Params?
     * @return Completable
     */
    fun execute(params: Params?): Completable {
        return buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
    }

    /**
     *
     * @param params Params?
     * @param observer DisposableCompletableObserver
     * @param observeOnUI Boolean
     */
    fun execute(params: Params? = null, observer: DisposableCompletableObserver, observeOnUI: Boolean = true) {
        val observable: Completable = if (observeOnUI) {
            buildUseCase(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
        } else {
            buildUseCase(params)
                .subscribeOn(Schedulers.from(threadExecutor))
        }
        return subscribe(observable.subscribeWith(observer))
    }
}