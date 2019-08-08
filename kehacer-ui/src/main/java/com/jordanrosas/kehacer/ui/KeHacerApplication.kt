package com.jordanrosas.kehacer.ui

import android.app.Application
import com.jordanrosas.kehacer.ui.manager.RealmDataBaseManager

class KeHacerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RealmDataBaseManager.initConfiguration(this)
    }
}