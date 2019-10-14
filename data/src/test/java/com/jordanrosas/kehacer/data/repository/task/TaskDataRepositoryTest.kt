package com.jordanrosas.kehacer.data.repository.task

import com.jordanrosas.kehacer.data.cache.TaskCacheSource
import com.jordanrosas.kehacer.data.cache.entities.TaskRealmEntity
import com.jordanrosas.kehacer.data.mapper.TaskRealMapper
import com.jordanrosas.kehacer.domain.repository.TaskRepository
import io.reactivex.Completable
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
    fun `Test insert Task`() {
        val taskDto = taskMocks.getTaskDto()
        Mockito.`when`(taskCacheSource.insert(anyObjectOrArgument())).thenReturn(
            Single.just(TaskRealmEntity())
        )
        taskRepository.insert(taskDto)
            .test()
            .assertNoErrors()
            .assertComplete()
    }

   private fun <T> anyObjectOrArgument(): T {
        Mockito.any<T>()
        return null as T
    }

    @Test
    fun `Test get Task List`() {
        val mapper = TaskRealMapper()
        val taskListDto = taskMocks.getTaskDtoList()
        val taskRealmList = ArrayList<TaskRealmEntity>()
        taskListDto.forEach {
            taskRealmList.add(mapper.mapTo(it))
        }

        Mockito.`when`(taskCacheSource.getTaskList()).thenReturn(
            Single.just(taskRealmList)
        )
        taskRepository.getTaskList()
            .test()
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `Test update Task`() {
        val taskDto = taskMocks.getTaskDto()
        Mockito.`when`(taskCacheSource.update(anyObjectOrArgument()))
            .thenReturn(Completable.complete())

        taskRepository.update(taskDto)
            .test()
            .assertNoErrors()
            .assertComplete()
    }

    @Test
    fun `Test delete Task`() {
        Mockito.`when`(taskCacheSource.delete(1))
            .thenReturn(Completable.complete())

        taskRepository.delete(1)
            .test()
            .assertNoErrors()
            .assertComplete()
    }
}