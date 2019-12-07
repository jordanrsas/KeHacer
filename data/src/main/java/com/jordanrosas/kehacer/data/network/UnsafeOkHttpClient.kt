package com.jordanrosas.kehacer.data.network

import android.util.Log
import com.jordanrosas.kehacer.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class UnsafeOkHttpClient {

    companion object {
        private const val CONNECTION_TIME_OUT: Long = 60

        private val interceptor by lazy {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            interceptor

        }

        private val headers by lazy {
            val params = HashMap<String, String>()
            params["Content-Type"] = "application/json"
            params
        }

        val unsafeOkHttpClient: OkHttpClient by lazy {

            val sslContext = SSLContext.getInstance("SSL")

            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) = Unit

                override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) = Unit

                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            })

            val sslSocketFactory = sslContext.apply {
                init(null, trustAllCerts, SecureRandom())
            }.socketFactory

            val builder = OkHttpClient.Builder()

            builder.addInterceptor { chain ->
                val request = chain.request()
                val myBuilder = request.newBuilder()

                if (headers.size > 0) {
                    for ((key, value) in headers) {
                        myBuilder.addHeader(key, value)
                        Log.i(key, value)
                    }
                }
                chain.proceed(myBuilder.build())
            }
                .readTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier { _, _ -> true }

            builder.build()
        }
    }
}