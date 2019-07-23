package com.jordanrosas.kehacer.domain.interactor

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.executor.ThreadExecutor
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Params, T>(
    val threadExecutor: ThreadExecutor,
    val postExecutionThread: PostExecutionThread
) : UseCase<Params, Single<T>>() {

    /**
     *
     * @param params Params?
     * @return Single<T>
     */
    fun execute(params: Params? = null): Single<T> {
        return buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
    }

    /**
     *
     * @param params Params?
     * @param observer DisposableSingleObserver<T>
     */
    fun execute(params: Params? = null, observer: DisposableSingleObserver<T>) {
        val observable = buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
        subscribe(observable.subscribeWith(observer))
    }
}