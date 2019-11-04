package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.executor.ThreadExecutor
import com.jordanrosas.kehacer.domain.interactor.ObservableUseCase
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Observable

/**
 * @property taskDataRepository TaskRepository
 * @constructor
 */
class InsertTask(
    private val taskDataRepository: TaskRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<TaskDto, TaskDto>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: TaskDto?): Observable<TaskDto> {
        return params?.let {
            taskDataRepository.insert(it)
        } ?: Observable.error(Exception())
    }

    /*override fun buildUseCase(params: TaskDto?): Single<TaskDto> {
        return params?.let {
            taskDataRepository.insert(it)
        } ?: Single.error(Exception())
    }*/
}