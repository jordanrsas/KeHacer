package com.jordanrosas.kehacer.ui.chores.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.jordanrosas.kehacer.domain.model.TaskDto
import com.jordanrosas.kehacer.domain.usecase.InsertTask
import com.jordanrosas.kehacer.ui.utils.Response
import io.reactivex.disposables.CompositeDisposable

class ChoresActivityViewModel(private val insertTask: InsertTask) : ViewModel() {

    private val insertTaskResponse = MutableLiveData<Response<TaskDto>>()

    private val disposable = CompositeDisposable()

    fun insertTask(params: TaskDto) {
        disposable.add(
            insertTask.observable(params)
                .doOnSubscribe {
                    insertTaskResponse.value = Response.loading()
                }
                .subscribe({ task ->
                    insertTaskResponse.value = Response.success(task)
                }, { error ->
                    insertTaskResponse.value = Response.error(error.message)
                })
        )
    }

    override fun onCleared() {
        disposable.dispose()
    }
}