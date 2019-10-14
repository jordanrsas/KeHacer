package com.jordanrosas.kehacer.data.mapper

import com.jordanrosas.kehacer.data.cache.entities.TaskRealmEntity
import com.jordanrosas.kehacer.data.model.TaskEntity
import com.jordanrosas.kehacer.domain.mapper.TransformMapper

class TaskRealMapper : TransformMapper<TaskEntity, TaskRealmEntity> {
    override fun mapTo(value: TaskEntity): TaskRealmEntity {
        return TaskRealmEntity(
            id = value.id,
            title = value.title,
            task = value.task,
            category = value.category,
            date = value.date,
            time = value.time
        )
    }

    override fun mapFrom(value: TaskRealmEntity): TaskEntity {
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