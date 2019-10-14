package com.jordanrosas.kehacer.data.cache

import com.jordanrosas.kehacer.data.cache.entities.TaskRealmEntity
import io.reactivex.Completable
import io.reactivex.Single

interface TaskCacheSource {
    fun insert(taskRealmEntity: TaskRealmEntity): Single<TaskRealmEntity>
    fun update(taskRealmEntity: TaskRealmEntity): Completable
    fun delete(id: Int): Completable
    fun getTaskList(): Single<List<TaskRealmEntity>>

    fun save(taskRealmEntity: TaskRealmEntity)
}