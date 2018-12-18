package com.zawhtetnaing.mmkunyi_app_assignment_zhn.network

interface JobsDataAgent {
    fun loadJobs(accessToken: String,
                 page: Int,
                 isForceRefreshed: Boolean): Unit
}