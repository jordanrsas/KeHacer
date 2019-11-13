package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UpdateTaskTest {
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Mock
    private lateinit var taskDataRepository: TaskRepository

    private lateinit var updateTaskTest: UpdateTask

    private lateinit var taskMocks: TaskMocks

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(postExecutionThread.getScheduler())
            .thenReturn(Schedulers.trampoline())

        updateTaskTest = UpdateTask(
            taskDataRepository,
            ImmediateThreadExecutor(),
            postExecutionThread
        )
        taskMocks = TaskMocks()
    }

    @Test
    fun `Update Task Result`() {
        val taskDto = taskMocks.getTaskDto()

        Mockito.`when`(taskDataRepository.update(taskDto))
            .thenReturn(Observable.just(true))
        updateTaskTest.observable(taskDto)
            .test()
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `Update Task Error Result`() {
        val taskDto = taskMocks.getTaskDto()

        Mockito.`when`(taskDataRepository.update(taskDto))
            .thenReturn(Observable.error(Exception()))

        updateTaskTest.observable(taskDto)
            .test()
            .assertError { error ->
                error is Exception
            }
    }
}