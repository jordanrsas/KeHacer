package com.jordanrosas.kehacer.data.cache.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class TaskRealmEntity constructor() : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var title: String = ""
    var task: String = ""
    var category: String = ""
    var date: String = ""
    var time: String = ""

    constructor(id: Int, title: String, task: String, category: String, date: String, time: String) : this() {
        this.id = id
        this.title = title
        this.task = task
        this.category = category
        this.date = date
        this.time = time
    }
}