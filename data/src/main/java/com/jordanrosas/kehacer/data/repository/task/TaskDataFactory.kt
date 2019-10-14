package com.jordanrosas.kehacer.data.repository.task

import com.jordanrosas.kehacer.data.cache.TaskCacheSource
import com.jordanrosas.kehacer.data.repository.task.datasource.TaskApiSource
import com.jordanrosas.kehacer.data.repository.task.datasource.TaskDiskSource
import com.jordanrosas.kehacer.data.repository.task.datasource.TaskSource

class TaskDataFactory(
    private val taskServices: TaskServices,
    private val taskCacheSource: TaskCacheSource
) {
    fun create(isRemote: Boolean): TaskSource {
        return if (isRemote) {
            TaskApiSource(taskServices, taskCacheSource)
        } else {
            TaskDiskSource(taskCacheSource)
        }
    }
}