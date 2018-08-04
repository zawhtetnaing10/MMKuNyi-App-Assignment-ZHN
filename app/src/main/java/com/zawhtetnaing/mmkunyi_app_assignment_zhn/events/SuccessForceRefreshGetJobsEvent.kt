package com.zawhtetnaing.mmkunyi_app_assignment_zhn.events

import android.app.job.JobScheduler
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO

class SuccessForceRefreshGetJobsEvent(val jobs: List<JobVO> = arrayListOf())
