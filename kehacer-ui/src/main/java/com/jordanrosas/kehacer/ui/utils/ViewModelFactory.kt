package com.jordanrosas.kehacer.ui.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jordanrosas.kehacer.domain.usecase.InsertTask
import com.jordanrosas.kehacer.ui.chores.viewmodel.ChoresActivityViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val insertTask: InsertTask) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChoresActivityViewModel(insertTask) as T
    }
}