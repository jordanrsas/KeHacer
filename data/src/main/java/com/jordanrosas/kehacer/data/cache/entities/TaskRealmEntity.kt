package com.jordanrosas.kehacer.data.cache.entities

data class TaskRealmEntity(
    var id: Int,
    var title: String,
    var task: String,
    var category: String,
    var date: String,
    var time: String
)