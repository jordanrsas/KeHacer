package com.jordanrosas.kehacer.data.repository.task

import com.jordanrosas.kehacer.data.mapper.TaskRealMapper
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Single

class TaskDataRepository(private val taskCacheSource: TaskCacheSource) : TaskRepository {

    override fun insert(task: TaskDto): Single<TaskDto> {
        val mapper = TaskRealMapper()
        return taskCacheSource.insert(mapper.mapTo(task)).map {
            return@map mapper.mapFrom(it)
        }
    }
}