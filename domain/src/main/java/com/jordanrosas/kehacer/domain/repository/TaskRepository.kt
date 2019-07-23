package com.jordanrosas.kehacer.domain.repository

import com.jordanrosas.kehacer.domain.model.TaskDto
import io.reactivex.Completable
import io.reactivex.Single

interface TaskRepository {
    fun insert(task: TaskDto): Single<TaskDto>
    fun update(task: TaskDto): Completable
    fun delete(id: Int): Completable
    fun getTaskList(): Single<List<TaskDto>>
}