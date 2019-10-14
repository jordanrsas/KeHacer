package com.jordanrosas.kehacer.data.mapper

import com.jordanrosas.kehacer.data.model.TaskEntity
import com.jordanrosas.kehacer.domain.mapper.TransformMapper
import com.jordanrosas.kehacer.domain.model.TaskDto

class TaskDataMapper : TransformMapper<TaskEntity, TaskDto> {
    override fun mapTo(value: TaskEntity): TaskDto {
        return TaskDto(
            id = value.id,
            title = value.title,
            task = value.task,
            category = value.category,
            date = value.date,
            time = value.time
        )
    }

    override fun mapFrom(value: TaskDto): TaskEntity {
        return TaskEntity(
            value.id,
            value.title,
            value.task,
            value.category,
            value.date,
            value.time
        )
    }
}