package com.jordanrosas.kehacer.data.cache.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TaskRealmEntity : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var title: String = ""
    var task: String = ""
    var category: String = ""
    var date: String = ""
    var time: String = ""
}