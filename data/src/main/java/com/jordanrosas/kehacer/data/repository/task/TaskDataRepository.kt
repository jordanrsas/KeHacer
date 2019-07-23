package com.jordanrosas.kehacer.data.repository.task

import com.jordanrosas.kehacer.data.mapper.TaskRealMapper
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Completable
import io.reactivex.Single

class TaskDataRepository(private val taskCacheSource: TaskCacheSource) : TaskRepository {

    override fun insert(task: TaskDto): Single<TaskDto> {
        val mapper = TaskRealMapper()
        return taskCacheSource.insert(mapper.mapTo(task)).map {
            return@map mapper.mapFrom(it)
        }
    }

    override fun update(task: TaskDto): Completable {
        val mapper = TaskRealMapper()
        return taskCacheSource.update(mapper.mapTo(task))
    }

    override fun delete(id: Int): Completable {
        return taskCacheSource.delete(id)
    }

    override fun getTaskList(): Single<List<TaskDto>> {
        val mapper = TaskRealMapper()
        return taskCacheSource.getTaskList().map { listTaskRealm ->
            val listTask = ArrayList<TaskDto>()
            listTaskRealm.forEach {
                listTask.add(mapper.mapFrom(it))
            }
            return@map listTask
        }
    }
}