package com.jordanrosas.kehacer.domain.mapper

interface TransformMapper<D, E> {
    fun mapTo(value: D): E
    fun mapFrom(value: E): D
}