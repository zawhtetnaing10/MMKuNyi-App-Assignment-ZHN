package com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.views

import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO

interface JobListView : BaseView {
    fun launchJobDetailsScreen(job: JobVO)
    fun launchPersonsAppliedScreen(job: JobVO)
    fun launchLoginScreen()
    fun launchRegisterScreen()
}