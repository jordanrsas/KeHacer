package com.jordanrosas.kehacer.domain.repository

import com.jordanrosas.kehacer.domain.model.TaskDto
import io.reactivex.Observable

interface TaskRepository {
    fun fromRemote(isRemote: Boolean)
    fun insert(task: TaskDto): Observable<TaskDto>
    fun update(task: TaskDto): Observable<Boolean>
    fun delete(id: Int): Observable<Boolean>
    fun getTaskList(): Observable<List<TaskDto>>
}