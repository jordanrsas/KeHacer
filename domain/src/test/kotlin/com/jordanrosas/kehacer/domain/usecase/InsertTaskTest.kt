package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class InsertTaskTest {
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Mock
    private lateinit var taskDataRepository: TaskRepository

    private lateinit var insertTaskTest: InsertTask

    private lateinit var taskMocks: TaskMocks

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(postExecutionThread.getScheduler())
            .thenReturn(Schedulers.trampoline())

        insertTaskTest = InsertTask(
            taskDataRepository,
            ImmediateThreadExecutor(),
            postExecutionThread
        )

        taskMocks = TaskMocks()
    }

    @Test
    fun `Insert Task Result`() {
        val taskDto = taskMocks.getTaskDto()

        Mockito.`when`(taskDataRepository.insert(taskDto))
            .thenReturn(Single.just(taskDto))

        insertTaskTest.execute(taskDto)
            .test()
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `Insert Task Error`() {
        val taskDto: TaskDto? = null
        insertTaskTest.execute(taskDto)
            .test()
            .assertError { error ->
                error is Exception
            }
    }

    @Test
    fun `Insert Task Success With Values`() {
        val taskDto = taskMocks.getTaskDto()

        Mockito.`when`(taskDataRepository.insert(taskDto))
            .thenReturn(Single.just(taskDto))

        insertTaskTest.execute(taskDto)
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValue { result ->
                result.id == taskDto.id &&
                        result.category == taskDto.category
            }
    }
}

