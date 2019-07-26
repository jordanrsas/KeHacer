package com.jordanrosas.kehacer.domain.usecase

import com.jordanrosas.kehacer.domain.executor.ThreadExecutor

class ImmediateThreadExecutor : ThreadExecutor {
    override fun execute(runnable: Runnable?) {
        runnable?.run()
    }
}