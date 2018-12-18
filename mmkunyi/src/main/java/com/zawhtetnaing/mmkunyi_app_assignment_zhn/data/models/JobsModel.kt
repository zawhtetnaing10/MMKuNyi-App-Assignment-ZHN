package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.models

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.*
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.events.SuccessForceRefreshGetJobsEvent
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.events.SuccessGetJobsEvent
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.result.Result
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class JobsModel : BaseModel {

    constructor(context: Context) : super(context) {
        EventBus.getDefault().register(this)
    }

    companion object {

        const val DUMMY_ACCESS_TOKEN: String = "b002c7e1a528b7cb460933fc2875e916"

        private const val EM_JOB_MODEL_EARLY_INVOCATION = "Jobs model is invoked before initializing"

        private var objInstance: JobsModel? = null

        fun initJobsModel(context: Context) {
            objInstance = JobsModel(context)
        }

        fun getInstance(): JobsModel {
            if (objInstance == null) {
                throw RuntimeException(EM_JOB_MODEL_EARLY_INVOCATION)
            }
            return objInstance!!
        }

    }

    private var mPage: Int = 1

    private var jobDataRepository: MutableMap<Int, JobVO> = mutableMapOf()


    suspend fun loadNewJobs(): Result<JobVO> {
        /*mTheAPI.loadJobs(DUMMY_ACCESS_TOKEN, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it?.let {
                        if (it.jobs.isNotEmpty()) {
                            insertJobsIntoDatabase(it.jobs)
                            jobListLiveData.value = it.jobs
                            mPage++
                        }
                    } ?: run {
                        errorLiveData.value = "The data cannot be loaded"
                    }
                }*/
        val response = mTheAPI.loadJobs(DUMMY_ACCESS_TOKEN, mPage)
        val result = response.await()
        if (result.jobs.isNotEmpty()) {
            return Result.Success(result.jobs)
            mPage++
        } else {
            return Result.Error("The data cannot be loaded")
        }
    }

    suspend fun forceRefreshLoadJobs(): Result<JobVO> {
        mPage = 1
        return loadNewJobs()
    }

    private fun insertJobsIntoDatabase(jobListFromNetwork: List<JobVO>) {
        val appllicantList = mutableListOf<ApplicantVO>()
        val interestedList = mutableListOf<InterestedVO>()
        val jobDurationList = mutableListOf<JobDurationVO>()
        val jobTagList = mutableListOf<JobTagVO>()
        val offerAmountList = mutableListOf<OfferAmountVO>()
        val relevantList = mutableListOf<RelevantVO>()
        val seekerSkillList = mutableListOf<SeekerSkillVO>()
        val viewedList = mutableListOf<ViewdVO>()
        val whyShouldHireList = mutableListOf<WhyShouldHireVO>()

        for (job in jobListFromNetwork) {
            for (applicant in job.applicants) {
                applicant.jobPostId = job.jobPostId
                appllicantList.add(applicant)

                for (seekerSkill in applicant.seekerSkill) {
                    if (seekerSkillList.contains(seekerSkill)) {
                        val seekerSkillInList = seekerSkillList.filter { it.skillId == seekerSkill.skillId }[0]
                        seekerSkillInList.interestedId = applicant.seekerId
                    } else {
                        seekerSkill.applicantId = applicant.seekerId
                        seekerSkillList.add(seekerSkill)
                    }
                }

                for (whyShouldHire in applicant.whyShouldHire) {
                    whyShouldHire.seekerId = applicant.seekerId
                    whyShouldHireList.add(whyShouldHire)
                }
            }

            for (interested in job.interested) {
                interested.jobPostId = job.jobPostId
                interestedList.add(interested)

                for (seekerSkill in interested.seekerSkill) {

                    seekerSkillList.asSequence().firstOrNull { it.skillId == seekerSkill.skillId }?.let {
                        val seekerSkillInList = seekerSkillList.filter { it.skillId == seekerSkill.skillId }[0]
                        seekerSkillInList.interestedId = interested.seekerId
                    } ?: run {
                        seekerSkill.interestedId = interested.seekerId
                        seekerSkillList.add(seekerSkill)
                    }
                }
            }

            job.jobDuration?.let { jobDurationList.add(it) }

            for (jobTag in job.jobTags) {
                jobTag.jobPostId = job.jobPostId
                jobTagList.add(jobTag)
            }

            job.offerAmount?.let { offerAmountList.add(it) }

            for (relevant in job.relevant) {
                relevant.jobPostId = job.jobPostId
                relevantList.add(relevant)

                for (seekerSkill in relevant.seekerSkill) {
                    seekerSkillList.asSequence().firstOrNull { it.skillId == seekerSkill.skillId }?.let {
                        seekerSkill.relevantId = relevant.seekerId
                    } ?: run {
                        seekerSkill.relevantId = relevant.seekerId
                        seekerSkillList.add(seekerSkill)
                    }
                }
            }

            for (requiredSkill in job.requiredSkills) {

                if (seekerSkillList.contains(requiredSkill)) {
                    seekerSkillList.asSequence().firstOrNull { it.skillId == requiredSkill.skillId }?.let {
                        val seekerSkillInList = seekerSkillList.filter { it.skillId == requiredSkill.skillId }[0]
                        seekerSkillInList.interestedId = requiredSkill.skillId
                    } ?: run {
                        requiredSkill.jobPostId = job.jobPostId
                        seekerSkillList.add(requiredSkill)
                    }
                }

            }

            for (viewed in job.viewed) {
                viewed.jobPostId = job.jobPostId
                viewedList.add(viewed)

                for (seekerSkill in viewed.seekerSkill) {

                    seekerSkillList.asSequence().firstOrNull { it.skillId == seekerSkill.skillId }?.let {
                        val seekerSkillInList = seekerSkillList.filter { it.skillId == seekerSkill.skillId }[0]
                        seekerSkillInList.interestedId = viewed.seekerId
                    } ?: run {
                        seekerSkill.viewedId = viewed.seekerId
                        seekerSkillList.add(seekerSkill)
                    }

                }

            }
        }

        // Insertion by sequence
        mTheDB.jobDurationDao().insertJobDuration(*(jobDurationList.toTypedArray()))
        mTheDB.offerAmountDao().insertOfferAmounts(*(offerAmountList.toTypedArray()))
        mTheDB.applicantDao().insertApplicants(*(appllicantList.toTypedArray()))
        mTheDB.interestedDao().insertInterested(*(interestedList.toTypedArray()))
        mTheDB.jobTagDao().insertJobTags(*(jobTagList.toTypedArray()))
        mTheDB.relevantDao().insertRelevants(*(relevantList.toTypedArray()))
        mTheDB.seekerSkillDao().insertSeekerSkills(*(seekerSkillList.toTypedArray()))
        mTheDB.viewedDao().insertViewed(*(viewedList.toTypedArray()))
        mTheDB.whyShouldHireDao().insertWhyShouldHire(*(whyShouldHireList.toTypedArray()))
        mTheDB.jobDao().insertJob(*(jobListFromNetwork.toTypedArray()))
    }

    fun findJobByIdLD(id: Int): MutableLiveData<JobVO> {
        val jobLD = MutableLiveData<JobVO>()
        mTheDB.jobDao().findJobById(id).observeForever {

            it?.let {
                //Retrieve by sequence
                it.applicants = mTheDB.applicantDao().getApplicantsByJobPostId(id)
                it.interested = mTheDB.interestedDao().getInterestedByJobPostID(id)
                it.jobTags = mTheDB.jobTagDao().getJobTagsByJobPostId(id)
                it.relevant = mTheDB.relevantDao().getRelevantByJobPostId(id)
                it.requiredSkills = mTheDB.seekerSkillDao().getSeekerSkillByJobPostId(id)
                it.viewed = mTheDB.viewedDao().getViewdByJobPostId(id)
            }

            jobLD.value = it
        }
        return jobLD
    }

    fun findSeekerSkillsAccordingToJobId(jobPostId: Int): List<SeekerSkillVO> = mTheDB.seekerSkillDao().getSeekerSkillByJobPostId(jobPostId)

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onSuccessGetJobsEvent(event: SuccessGetJobsEvent) {
        insertJobsIntoDatabase(event.jobs)
        mPage++
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onSuccessForceRefreshedGetJobsEvent(event: SuccessForceRefreshGetJobsEvent) {
        insertJobsIntoDatabase(event.jobs)
        mPage++
    }


}