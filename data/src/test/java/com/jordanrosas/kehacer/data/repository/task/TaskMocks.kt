package com.jordanrosas.kehacer.data.repository.task

import com.jordanrosas.kehacer.data.cache.entities.TaskRealmEntity
import com.jordanrosas.kehacer.domain.model.TaskDto

class TaskMocks {
    fun getTaskRealEntity(): TaskRealmEntity {
        return TaskRealmEntity().apply {
            id = 2
            title = "Titulo"
            task = "Tarea 1"
            category = "Importante"
            date = "20 enero 2019"
            time = "19:00"
        }
    }

    fun getTaskDto() = TaskDto(
        2,
        "Titulo",
        "Tarea 1",
        "Importante",
        "20 enero 2019",
        "19:00"
    )

    fun getTaskRealEntityList(): List<TaskRealmEntity> {
        val taskRealmEntityList = ArrayList<TaskRealmEntity>()
        taskRealmEntityList.add(getTaskRealEntity())
        taskRealmEntityList.add(getTaskRealEntity())
        taskRealmEntityList.add(getTaskRealEntity())
        taskRealmEntityList.add(getTaskRealEntity())
        taskRealmEntityList.add(getTaskRealEntity())
        taskRealmEntityList.add(getTaskRealEntity())
        return taskRealmEntityList
    }

    fun getTaskDtoList(): List<TaskDto> {
        val taskDtoList = ArrayList<TaskDto>()
        taskDtoList.add(getTaskDto())
        taskDtoList.add(getTaskDto())
        taskDtoList.add(getTaskDto())
        taskDtoList.add(getTaskDto())
        taskDtoList.add(getTaskDto())
        taskDtoList.add(getTaskDto())
        return taskDtoList
    }
}