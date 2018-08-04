package com.zawhtetnaing.mmkunyi_app_assignment_zhn.network

import com.google.gson.Gson
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.events.ApiErrorEvent
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.events.SuccessGetJobsEvent
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.events.SuccessForceRefreshGetJobsEvent
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.network.responses.GetJobsResponse
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.utils.MMKuNyiConstants
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitDataAgentImpl private constructor() : JobsDataAgent {

    private var mApi: JobsApi? = null

    companion object {

        private var objInstance: RetrofitDataAgentImpl? = null

        fun getInstance(): RetrofitDataAgentImpl {
            if (objInstance == null) {
                objInstance = RetrofitDataAgentImpl()
            }
            return objInstance!!
        }
    }

    init {
        val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(MMKuNyiConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()

        mApi = retrofit.create(JobsApi::class.java)
    }

    override fun loadJobs(accessToken: String, page: Int, isForceRefreshed: Boolean) {
        val call = mApi!!.loadJobs(accessToken, page)
        call.enqueue(object : Callback<GetJobsResponse> {

            override fun onResponse(call: Call<GetJobsResponse>?, response: Response<GetJobsResponse>?) {
                val response = response!!.body()

                if (response != null && response.isResponseOk()) {
                    if (!isForceRefreshed) {
                        val event = SuccessGetJobsEvent(response.jobs)
                        EventBus.getDefault().post(event)
                    } else if (isForceRefreshed) {
                        val event = SuccessForceRefreshGetJobsEvent(response.jobs)
                        EventBus.getDefault().post(event)
                    }
                }
                if (response == null) {
                    val event = ApiErrorEvent(MMKuNyiConstants.EMPTY_RESPONSE_ERROR)
                    EventBus.getDefault().post(event)
                }else{
                    val event = ApiErrorEvent(response.message)
                    EventBus.getDefault().post(event)
                }
            }

            override fun onFailure(call: Call<GetJobsResponse>?, t: Throwable?) {
                val event = ApiErrorEvent(t?.message.toString())
                EventBus.getDefault().post(event)
            }

        })


    }
}