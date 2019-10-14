package com.jordanrosas.kehacer.data.repository.task.datasource

import com.jordanrosas.kehacer.data.cache.TaskCacheSource
import com.jordanrosas.kehacer.data.mapper.TaskRealMapper
import com.jordanrosas.kehacer.data.model.TaskEntity
import io.reactivex.Observable

class TaskDiskSource(private val taskCacheSource: TaskCacheSource) : TaskSource {

    private val mapper = TaskRealMapper()

    override fun insert(taskEntity: TaskEntity): Observable<TaskEntity> {
        val entity = mapper.mapTo(taskEntity)
        return taskCacheSource.insert(entity).toObservable().map {
            return@map mapper.mapFrom(it)
        }
    }

    override fun update(taskEntity: TaskEntity): Observable<Boolean> {
        return taskCacheSource.update(mapper.mapTo(taskEntity)).toObservable<Boolean>()
    }

    override fun delete(id: Int): Observable<Boolean> {
        return taskCacheSource.delete(id).toObservable()
    }

    override fun getTaskList(): Observable<List<TaskEntity>> {
        return taskCacheSource.getTaskList().toObservable().map {
            val taskEntityList = ArrayList<TaskEntity>()
            it.forEach {
                taskEntityList.add(mapper.mapFrom(it))
            }
            return@map taskEntityList
        }
    }
}