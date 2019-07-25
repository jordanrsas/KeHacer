package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.PostExecutionThread
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class InsertTaskTest {
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun initialize(){
        MockitoAnnotations.initMocks(this)
    }
}