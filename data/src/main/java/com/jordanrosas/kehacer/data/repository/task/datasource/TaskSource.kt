package com.jordanrosas.kehacer.data.repository.task.datasource

import com.jordanrosas.kehacer.data.model.TaskEntity
import io.reactivex.Observable

interface TaskSource {
    fun insert(taskEntity: TaskEntity): Observable<TaskEntity>
    fun update(taskEntity: TaskEntity): Observable<Boolean>
    fun delete(id: Int): Observable<Boolean>
    fun getTaskList(): Observable<List<TaskEntity>>
}