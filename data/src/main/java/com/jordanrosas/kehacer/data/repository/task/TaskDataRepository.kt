package com.jordanrosas.kehacer.data.repository.task

import com.jordanrosas.kehacer.data.mapper.TaskDataMapper
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Observable

class TaskDataRepository(
    private val taskDataFactory: TaskDataFactory
) : TaskRepository {

    private val taskMapper = TaskDataMapper()
    private var isRemote = false

    override fun fromRemote(isRemote: Boolean) {
        this.isRemote = isRemote
    }

    override fun insert(task: TaskDto): Observable<TaskDto> {
        val taskEntity = taskMapper.mapFrom(task)

        return taskDataFactory
            .create(isRemote)
            .insert(taskEntity)
            .map {
                return@map taskMapper.mapTo(it)
            }
    }

    override fun update(task: TaskDto): Observable<Boolean> {
        val taskEntity = taskMapper.mapFrom(task)
        return taskDataFactory
            .create(isRemote)
            .update(taskEntity)
    }

    override fun delete(id: Int): Observable<Boolean> {
        return taskDataFactory
            .create(isRemote)
            .delete(id)
    }

    override fun getTaskList(): Observable<List<TaskDto>> {
        return taskDataFactory
            .create(isRemote)
            .getTaskList()
            .map { listTaskEntity ->
                val listTaskDto = ArrayList<TaskDto>()
                listTaskEntity.forEach {
                    listTaskDto.add(taskMapper.mapTo(it))
                }
                return@map listTaskDto
            }
    }
}