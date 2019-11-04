package com.jordanrosas.kehacer.domain.interactor

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

abstract class ObservableUseCase<Params, T> (
    val threadExecutor: ThreadExecutor,
    val postExecutionThread: PostExecutionThread
) : UseCase<Params, Observable<T>>() {

    fun observable(params: Params? = null): Observable<T> {
        return buildUseCase(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
    }
}