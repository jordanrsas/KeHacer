package com.jordanrosas.kehacer.data.repository.task

import com.jordanrosas.kehacer.data.cache.entities.TaskRealmEntity
import com.jordanrosas.kehacer.domain.model.TaskDto

class TaskMocks {
    fun getTaskRealEntity() = TaskRealmEntity(
        2,
        "Titulo",
        "Tarea 1",
        "Importante",
        "20 enero 2019",
        "19:00"
    )

    fun getTaskDto() = TaskDto(
        2,
        "Titulo",
        "Tarea 1",
        "Importante",
        "20 enero 2019",
        "19:00"
    )

}