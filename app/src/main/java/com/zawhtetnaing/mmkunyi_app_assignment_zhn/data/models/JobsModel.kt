package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.models

import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.events.SuccessForceRefreshGetJobsEvent
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.events.SuccessGetJobsEvent
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.network.JobsDataAgent
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.network.RetrofitDataAgentImpl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class JobsModel private constructor() {

    companion object {

        const val DUMMY_ACCESS_TOKEN: String = "b002c7e1a528b7cb460933fc2875e916"

        private var objInstance: JobsModel? = null
        fun getInstance(): JobsModel {
            if (objInstance == null) {
                objInstance = JobsModel()
            }
            val i = objInstance
            return i!!
        }
    }

    init {
        EventBus.getDefault().register(this)
    }

    private var mPage: Int = 1

    private var jobDataRepository: MutableMap<Int, JobVO> = mutableMapOf()

    fun findJobById(id: Int): JobVO? = jobDataRepository[id]

    fun loadNewJobs() {
        RetrofitDataAgentImpl.getInstance().loadJobs(DUMMY_ACCESS_TOKEN, mPage, false)
    }

    fun forceRefreshLoadJobs() {
        mPage = 1
        RetrofitDataAgentImpl.getInstance().loadJobs(DUMMY_ACCESS_TOKEN, mPage, true)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onSuccessGetJobsEvent(event: SuccessGetJobsEvent) {
        setDataIntoRepository(event.jobs)
        mPage++
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onSuccessForceRefreshedGetJobsEvent(event: SuccessForceRefreshGetJobsEvent) {
        setDataIntoRepository(event.jobs)
        mPage++
    }

    private fun setDataIntoRepository(jobList: List<JobVO>) {
        jobList.forEach { jobDataRepository[it.jobPostId] = it }
    }
}