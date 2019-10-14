package com.jordanrosas.kehacer.data.repository.task.datasource

import com.jordanrosas.kehacer.data.cache.TaskCacheSource
import com.jordanrosas.kehacer.data.mapper.TaskRealMapper
import com.jordanrosas.kehacer.data.model.TaskEntity
import com.jordanrosas.kehacer.data.repository.task.TaskServices
import io.reactivex.Observable

class TaskApiSource(
    private val taskServices: TaskServices,
    private val taskCacheSource: TaskCacheSource
) : TaskSource {

    private val mapper = TaskRealMapper()

    override fun insert(task: TaskEntity): Observable<TaskEntity> {
        return taskServices
            .insert(task)
            .concatMap {
                if (it.isSuccessful) {
                    return@concatMap Observable.just(it.body() as TaskEntity)
                } else {
                    return@concatMap Observable.error<TaskEntity>(Throwable(it.errorBody().toString()))
                }
            }.doOnNext {
                taskCacheSource.save(mapper.mapTo(it))
            }
    }

    override fun update(taskEntity: TaskEntity): Observable<Boolean> {
        return taskServices
            .update(taskEntity)
            .concatMap {
                if (it) {
                    return@concatMap Observable.just(it)
                } else {
                    return@concatMap Observable.error<Boolean>(Throwable("Error"))
                }
            }.doOnNext {
                taskCacheSource.update(mapper.mapTo(taskEntity))
            }
    }

    override fun delete(id: Int): Observable<Boolean> {
        return taskServices
            .delete(id)
            .concatMap {
                if (it) {
                    return@concatMap Observable.just(it)
                } else {
                    return@concatMap Observable.error<Boolean>(Throwable("Error"))
                }
            }.doOnNext {
                taskCacheSource.delete(id)
            }
    }

    override fun getTaskList(): Observable<List<TaskEntity>> {
        return taskServices
            .getTaskList()
            .concatMap {
                if (it.isSuccessful) {
                    val res = it.body() as List<TaskEntity>
                    return@concatMap Observable.just(res)
                } else {
                    return@concatMap Observable.error<List<TaskEntity>>(Throwable(it.errorBody().toString()))
                }
            }
    }
}