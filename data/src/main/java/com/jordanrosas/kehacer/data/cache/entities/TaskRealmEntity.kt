package com.jordanrosas.kehacer.data.cache.entities

import io.realm.RealmObject

class TaskRealmEntity : RealmObject() {
    var id: Int = 0
    var title: String? = ""
    var task: String? = ""
    var category: String? = ""
    var date: String? = ""
    var time: String? = ""
}