package com.zawhtetnaing.mmkunyi_app_assignment_zhn.network

import com.zawhtetnaing.mmkunyi_app_assignment_zhn.network.responses.GetJobsResponse
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.utils.MMKuNyiConstants
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url
import java.util.*

interface JobsApi {

    @FormUrlEncoded
    @POST(MMKuNyiConstants.GET_JOBS)
    fun loadJobs(@Field(MMKuNyiConstants.PARAM_ACCESS_TOKEN) accessToken: String,
                 @Field(MMKuNyiConstants.PARAM_PAGE) page: Int): Deferred<GetJobsResponse>

}