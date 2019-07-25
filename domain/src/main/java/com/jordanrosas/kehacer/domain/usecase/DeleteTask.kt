package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.executor.ThreadExecutor
import com.jordanrosas.kehacer.domain.interactor.CompletableUseCase
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Completable

class DeleteTask(
    private val taskDataRepository: TaskRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Int>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Int?): Completable {
        return params?.let {
            taskDataRepository.delete(it)
        } ?: Completable.error(Exception())
    }
}