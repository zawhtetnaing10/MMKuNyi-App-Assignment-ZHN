package com.zawhtetnaing.mmkunyi_app_assignment_zhn

import android.app.Application
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.models.JobsModel

class MMKunyiApp : Application() {

    override fun onCreate() {
        super.onCreate()

        JobsModel.initJobsModel(applicationContext)
    }

}