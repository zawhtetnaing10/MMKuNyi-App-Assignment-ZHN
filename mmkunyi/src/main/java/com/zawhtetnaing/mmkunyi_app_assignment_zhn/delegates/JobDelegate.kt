package com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates

import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO

interface JobDelegate {
    fun onTapJobs(jobData: JobVO?)
    fun onTapPersonsApplied(jobData:JobVO?)
}