package com.jordanrosas.kehacer.ui.manager

import android.content.Context
import com.jordanrosas.kehacer.data.manager.DataBaseManager
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmDataBaseManager : DataBaseManager {

    companion object {
        private const val SCHEMA_VERSION: Long = 1

        fun initConfiguration(context: Context) {
            Realm.init(context)
            val realmConfiguration = RealmConfiguration.Builder()
                .name("kehacerDB")
                .schemaVersion(SCHEMA_VERSION)
                .build()
            Realm.setDefaultConfiguration(realmConfiguration)
        }
    }

    override fun getInstance(): Realm =
        Realm.getDefaultInstance()

}