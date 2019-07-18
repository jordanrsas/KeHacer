package com.jordanrosas.kehacer.data.repository.task

import com.jordanrosas.kehacer.data.cache.entities.TaskRealmEntity
import com.jordanrosas.kehacer.data.mapper.TaskRealMapper
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TaskDataRepositoryTest {

    @Mock
    lateinit var taskCacheSource: TaskCacheSource

    lateinit var taskRepository: TaskRepository

    @Before
    fun initialize() {
        MockitoAnnotations.initMocks(this)
        taskRepository = TaskDataRepository(taskCacheSource)
    }

    @Test
    fun insertTaskSuccess() {
        val taskRealEntity = TaskRealmEntity(
            2,
            "Titulo",
            "Tarea 1",
            "Importante",
            "20 enero 2019",
            "19:00"
        )

        val taskDto = TaskDto(
            2,
            "Titulo",
            "Tarea 1",
            "Importante",
            "20 enero 2019",
            "19:00"
        )

        val mapper = TaskRealMapper()

        Mockito.`when`(taskCacheSource.insert(taskRealEntity)).thenReturn(
            Single.just(mapper.mapTo(taskDto))
        )

        taskRepository.insert(taskDto).test().assertNoErrors().assertComplete()

    }
}