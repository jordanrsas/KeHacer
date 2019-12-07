package com.jordanrosas.kehacer.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient : ApiClientFactory{
    companion object {
        private val URL = "https://aws.kehacer.com/"

        private val gson: Gson by lazy {
            GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .disableHtmlEscaping()
                .create()
        }
    }

    private val retrofitClient: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(UnsafeOkHttpClient.unsafeOkHttpClient)
            .baseUrl(URL)
            .build()
    }

    override fun <T> makeConnectionApiService(serviceClass: Class<T>): T {
        return retrofitClient.create(serviceClass)
    }
}





