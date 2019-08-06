package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetTaskListTest {
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Mock
    private lateinit var taskDataRepository: TaskRepository

    private lateinit var getTaskList: GetTaskList

    private lateinit var taskMocks: TaskMocks

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(postExecutionThread.getScheduler())
            .thenReturn(Schedulers.trampoline())
        taskMocks = TaskMocks()

        getTaskList = GetTaskList(
            taskDataRepository,
            ImmediateThreadExecutor(),
            postExecutionThread
        )
    }

    @Test
    fun `Test Get Task List Result`() {
        val taskdtoList = taskMocks.getTaskDtoList()
        Mockito.`when`(taskDataRepository.getTaskList())
            .thenReturn(Single.just(taskdtoList))

        getTaskList.execute()
            .test()
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `Test Get Task List Error Result`() {
        Mockito.`when`(taskDataRepository.getTaskList())
            .thenReturn(Single.error(Exception()))

        getTaskList.execute()
            .test()
            .assertError { error ->
                error is Exception
            }
    }

    @Test
    fun `Test Get Task List With Values`() {
        val taskdtoList = taskMocks.getTaskDtoList()
        Mockito.`when`(taskDataRepository.getTaskList())
            .thenReturn(Single.just(taskdtoList))

        getTaskList.execute()
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValue { result ->
                result.size == taskdtoList.size &&
                        result.elementAt(0) == taskdtoList.elementAt(0)
            }
    }

}
