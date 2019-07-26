package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.executor.ThreadExecutor
import com.jordanrosas.kehacer.domain.interactor.SingleUseCase
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Single

/**
 * @property taskDataRepository TaskRepository
 * @constructor
 */
class InsertTask(
    private val taskDataRepository: TaskRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<TaskDto, TaskDto>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: TaskDto?): Single<TaskDto> {
        return params?.let {
            taskDataRepository.insert(it)
        } ?: Single.error(Exception())
    }
}