package com.jordanrosas.kehacer.data.repository.task

import com.jordanrosas.kehacer.data.mapper.TaskRealMapper
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TaskDataRepositoryTest {

    @Mock
    private lateinit var taskCacheSource: TaskCacheSource

    private lateinit var taskRepository: TaskRepository
    private lateinit var taskMocks: TaskMocks

    @Before
    fun initialize() {
        MockitoAnnotations.initMocks(this)
        taskMocks = TaskMocks()
        taskRepository = TaskDataRepository(taskCacheSource)
    }

    @Test
    fun `Test insert`() {
        val mapper = TaskRealMapper()
        val taskRealEntity = taskMocks.getTaskRealEntity()
        val taskDto = taskMocks.getTaskDto()
        Mockito.`when`(taskCacheSource.insert(taskRealEntity)).thenReturn(
            Single.just(mapper.mapTo(taskDto))
        )
        taskRepository.insert(taskDto).test().assertNoErrors().assertComplete()
    }
}