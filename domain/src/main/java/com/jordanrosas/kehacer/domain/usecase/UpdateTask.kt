package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.executor.ThreadExecutor
import com.jordanrosas.kehacer.domain.interactor.ObservableUseCase
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Observable

class UpdateTask(
    private val taskDataRepository: TaskRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<TaskDto, Boolean>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: TaskDto?): Observable<Boolean> {
        return params?.let {
            taskDataRepository.update(it)
        } ?: Observable.error(Exception())
    }

    /*override fun buildUseCase(params: TaskDto?): Completable {
        return params?.let {
            taskDataRepository.update(it)
        } ?: Completable.error(Exception())
    }*/
}