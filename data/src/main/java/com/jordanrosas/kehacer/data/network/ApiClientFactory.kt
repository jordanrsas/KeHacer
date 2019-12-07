package com.jordanrosas.kehacer.data.network

interface ApiClientFactory {
    fun <T> makeConnectionApiService(serviceClass: Class<T>): T
}