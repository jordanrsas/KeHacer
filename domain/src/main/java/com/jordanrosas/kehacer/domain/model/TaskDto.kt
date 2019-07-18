package com.jordanrosas.kehacer.domain.model

data class TaskDto(
    var id: Int,
    var title: String,
    var task: String,
    var category: String,
    var date: String,
    var time: String
)