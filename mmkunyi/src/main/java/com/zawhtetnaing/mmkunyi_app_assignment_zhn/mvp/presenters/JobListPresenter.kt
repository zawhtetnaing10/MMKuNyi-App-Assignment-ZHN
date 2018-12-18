package com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.presenters

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.models.JobsModel
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates.BeforeLoginDelegate
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates.JobDelegate
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.views.JobListView
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class JobListPresenter : BasePresenter<JobListView>(), JobDelegate, BeforeLoginDelegate {

    private lateinit var jobsListLD: MutableLiveData<List<JobVO>>
    private lateinit var forceRefreshJobListLD: MutableLiveData<List<JobVO>>

    override fun initPresenter(mView: JobListView) {
        super.initPresenter(mView)

        jobsListLD = MutableLiveData()
        forceRefreshJobListLD = MutableLiveData()

    }

    fun getLiveData(): LiveData<List<JobVO>> = jobsListLD

    fun getRefreshLiveData(): LiveData<List<JobVO>> = forceRefreshJobListLD

    fun onSwipeGesture() {
        GlobalScope.launch {
            JobsModel.getInstance().forceRefreshLoadJobs()
        }
    }

    fun uiReadyAndNetworkAvailable() {
        GlobalScope.launch (Dispatchers.Main){
            JobsModel.getInstance().loadNewJobs().apply {
                when (this) {
                    is Result.Success -> {
                        jobsListLD.value = this.data
                    }
                    is Result.Error -> {
                        errorLD.value = this.message
                    }
                }
            }
        }
    }

    override fun onTapJobs(jobData: JobVO?) {
        mView!!.launchJobDetailsScreen(jobData!!)
    }

    override fun onTapPersonsApplied(jobData: JobVO?) {
        mView!!.launchPersonsAppliedScreen(jobData!!)
    }

    override fun onTapLogin() {
        mView!!.launchLoginScreen()
    }

    override fun onTapRegister() {
        mView!!.launchRegisterScreen()
    }


}