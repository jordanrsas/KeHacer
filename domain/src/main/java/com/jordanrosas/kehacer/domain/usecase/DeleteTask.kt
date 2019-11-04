package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.executor.ThreadExecutor
import com.jordanrosas.kehacer.domain.interactor.ObservableUseCase
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Observable
import io.reactivex.Observable.error

class DeleteTask(
    private val taskDataRepository: TaskRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<Int, Boolean>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Int?): Observable<Boolean> {
        return params?.let {
            taskDataRepository.delete(it)
        } ?: error(Exception())
    }

    /*override fun buildUseCase(params: Int?): Observable {
        return params?.let {
            taskDataRepository.delete(it)
        } ?: Completable.error(Exception())
    }*/
}