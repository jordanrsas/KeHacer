package com.jordanrosas.kehacer.data.repository.task

import com.jordanrosas.kehacer.data.model.TaskEntity
import io.reactivex.Observable
import retrofit2.Response

interface TaskServices {
    fun insert(taskEntity: TaskEntity): Observable<Response<TaskEntity>>
    fun update(taskEntity: TaskEntity): Observable<Boolean>
    fun delete(id: Int): Observable<Boolean>
    fun getTaskList(): Observable<Response<List<TaskEntity>>>
}