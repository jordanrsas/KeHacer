package com.jordanrosas.kehacer.ui.utils

class Response<T>
private constructor(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T): Response<T> {
            return Response(Status.SUCCESS, data, null)
        }

        fun <T> error(error: String?): Response<T> {
            return Response(Status.ERROR, null, error)
        }

        fun <T> loading(): Response<T> {
            return Response(Status.LOADING, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}