package com.jordanrosas.kehacer.domain.repository

import com.jordanrosas.kehacer.domain.model.TaskDto
import io.reactivex.Single

interface TaskRepository {
    fun insert(task: TaskDto): Single<TaskDto>
    fun update(task: TaskDto)
    fun delete(id: Int)
    fun getTaskList(): Single<List<TaskDto>>
}