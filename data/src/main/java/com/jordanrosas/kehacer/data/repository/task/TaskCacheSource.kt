package com.jordanrosas.kehacer.data.repository.task

import com.jordanrosas.kehacer.data.cache.entities.TaskRealmEntity
import io.reactivex.Single

interface TaskCacheSource {
    fun insert(taskRealmEntity: TaskRealmEntity): Single<TaskRealmEntity>
    fun update(taskRealmEntity: TaskRealmEntity)
    fun delete(id: Int)
    fun getTaskList(): Single<List<TaskRealmEntity>>
}