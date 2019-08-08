package com.jordanrosas.kehacer.data.cache

import com.jordanrosas.kehacer.data.cache.entities.TaskRealmEntity
import com.jordanrosas.kehacer.data.manager.DataBaseManager
import com.jordanrosas.kehacer.data.repository.task.TaskCacheSource
import io.reactivex.Completable
import io.reactivex.Single

class TaskCacheDataSource(private val dataBaseManager: DataBaseManager) : TaskCacheSource {


    /*
    Roboelectric

     */
    override fun insert(taskRealmEntity: TaskRealmEntity): Single<TaskRealmEntity> {
        return Single.create { emitter ->
            dataBaseManager.getInstance().use {
                val result = it.copyToRealmOrUpdate(taskRealmEntity)
                if (result != null) {
                    emitter.onSuccess(result)
                } else {
                    emitter.onError(Throwable("Insert Fail"))
                }
            }
        }
    }

    override fun update(taskRealmEntity: TaskRealmEntity): Completable {
    }

    override fun delete(id: Int): Completable {
    }

    override fun getTaskList(): Single<List<TaskRealmEntity>> {
    }
}