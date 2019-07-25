package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.executor.ThreadExecutor
import com.jordanrosas.kehacer.domain.interactor.CompletableUseCase
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Completable

class UpdateTask(
    private val taskDataRepository: TaskRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<TaskDto>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: TaskDto?): Completable {
        return params?.let {
            taskDataRepository.update(it)
        } ?: Completable.error(Exception())
    }
}