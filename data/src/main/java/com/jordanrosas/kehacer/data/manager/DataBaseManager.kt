package com.jordanrosas.kehacer.data.manager

import io.realm.Realm

interface DataBaseManager {
    fun getInstance(): Realm
}