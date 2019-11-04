package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.executor.ThreadExecutor
import com.jordanrosas.kehacer.domain.interactor.ObservableUseCase
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Observable

class GetTaskList(
    private val taskDataRepository: TaskRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<Void, List<TaskDto>>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Void?): Observable<List<TaskDto>> {
        return taskDataRepository.getTaskList()
    }
    /*override fun buildUseCase(params: Void?): Single<List<TaskDto>> {
        return taskDataRepository.getTaskList()
    }*/
}