package com.jordanrosas.kehacer.data.mapper

import com.jordanrosas.kehacer.data.cache.entities.TaskRealmEntity
import com.jordanrosas.kehacer.domain.mapper.TransformMapper
import com.jordanrosas.kehacer.domain.model.TaskDto

class TaskRealMapper : TransformMapper<TaskDto, TaskRealmEntity> {
    override fun mapTo(value: TaskDto): TaskRealmEntity {
        return TaskRealmEntity().apply {
            id = value.id
            title = value.title
            task = value.task
            category = value.category
            date = value.date
            time = value.time
        }
    }

    override fun mapFrom(value: TaskRealmEntity): TaskDto {
        return TaskDto(
            value.id,
            value.title,
            value.task,
            value.category,
            value.date,
            value.time
        )
    }
}