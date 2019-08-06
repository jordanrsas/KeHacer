package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DeleteTaskTest {
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Mock
    private lateinit var taskDataRepository: TaskRepository

    private lateinit var deleteTaskTest: DeleteTask


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(postExecutionThread.getScheduler())
            .thenReturn(Schedulers.trampoline())

        deleteTaskTest = DeleteTask(
            taskDataRepository,
            ImmediateThreadExecutor(),
            postExecutionThread
        )
    }

    @Test
    fun `Delete Task Result`() {
        Mockito.`when`(taskDataRepository.delete(2))
            .thenReturn(Completable.complete())

        deleteTaskTest.execute(2)
            .test()
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `Delete Task Error Result`() {
        Mockito.`when`(taskDataRepository.delete(2))
            .thenReturn(Completable.error(Exception()))

        deleteTaskTest.execute(2)
            .test()
            .assertError { error ->
                error is Exception
            }
    }
}
