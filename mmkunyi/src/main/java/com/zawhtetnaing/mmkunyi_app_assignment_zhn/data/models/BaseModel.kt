package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.models

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.network.JobsApi
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.MMKunyiDB
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.utils.MMKuNyiConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseModel {

    protected lateinit var mTheDB: MMKunyiDB
    protected lateinit var mTheAPI: JobsApi

    constructor(context: Context) {

        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(MMKuNyiConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()

        mTheAPI = retrofit.create(JobsApi::class.java)
        mTheDB = MMKunyiDB.getMMKunyiDB(context)
    }
}