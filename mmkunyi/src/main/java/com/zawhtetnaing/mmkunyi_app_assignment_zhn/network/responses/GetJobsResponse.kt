package com.zawhtetnaing.mmkunyi_app_assignment_zhn.network.responses

import com.google.gson.annotations.SerializedName
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO

class GetJobsResponse(@SerializedName("code") val code: Int = 0,
                      @SerializedName("message") val message: String = "",
                      @SerializedName("apiVersion") val apiVersion: String = "",
                      @SerializedName("jobs") val jobs: List<JobVO> = arrayListOf()) {

    fun isResponseOk(): Boolean = (code == 200 && jobs != null)

}