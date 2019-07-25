package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.executor.ThreadExecutor
import com.jordanrosas.kehacer.domain.interactor.SingleUseCase
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Single

class GetTaskList(
    private val taskDataRepository: TaskRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<Void, List<TaskDto>>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Void?): Single<List<TaskDto>> {
        return taskDataRepository.getTaskList()
    }
}