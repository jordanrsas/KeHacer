package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.model.TaskDto

class TaskMocks {
    fun getTaskDto() = TaskDto(
        2,
        "Titulo",
        "Tarea 1",
        "Importante",
        "20 enero 2019",
        "19:00"
    )

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