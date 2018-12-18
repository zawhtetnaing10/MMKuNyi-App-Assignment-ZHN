package com.zawhtetnaing.mmkunyi_app_assignment_zhn.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.presenters.JobDetailsPresenter
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.views.JobDetailsView
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.utils.MMKuNyiConstants
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.pods.EmptyViewPod
import kotlinx.android.synthetic.main.activity_job_details.*

class JobDetailsActivity : BaseActivity(), JobDetailsView {

    private var mJobData: JobVO? = null
    private lateinit var mPresenter: JobDetailsPresenter

    companion object {
        const val JOB_ID_EXTRA = "jobIDExtra"

        fun getIntent(context: Context, jobPostId: Int): Intent {
            val intent = Intent(context, JobDetailsActivity::class.java)
            intent.putExtra(JOB_ID_EXTRA, jobPostId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_details)

        mPresenter = ViewModelProviders.of(this).get(JobDetailsPresenter::class.java)
        mPresenter.initPresenter(this)

        val jobId: Int = intent.getIntExtra(JOB_ID_EXTRA, 0)

        setSupportActionBar(jobDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        jobDetailsAppbar.addOnOffsetChangedListener { _, verticalOffset ->
            if (verticalOffset == 0) {
                jobDetailsCollapsingToolbarLayout.isTitleEnabled = false
                jobDetailsToolbar.title = ""
            } else {
                jobDetailsCollapsingToolbarLayout.isTitleEnabled = true
            }
        }

        mPresenter.onUIReady(jobId).observe(this, Observer {
            displayJobDetails(it!!)
        })

        mPresenter.getErrorLiveData().observe(this, this)
    }

    private fun displayJobDetails(jobData: JobVO) {
        tvjobDetailsDescription.text = jobData!!.shortDesc
        tvjobDetailsFullDescription.text = jobData!!.fullDesc
        tvjobDetailsLocation.text = jobData!!.location
        tvjobDetailsPayment.text = MMKuNyiConstants.offerAmountAndDuration(jobData.offerAmount?.offerAmount, jobData.offerAmount?.duration)
        tvjobDetailsPeriod.text = MMKuNyiConstants.totalWorkingDays(jobData.jobDuration?.totalWorkingDays)
        tvjobDetailsVacancy.text = MMKuNyiConstants.vacancy(jobData.availablePostCount)

        tvRequiredSkill1.text = jobData!!.requiredSkills[0].skillName
        if (jobData!!.requiredSkills.size == 2) tvRequiredSkill2.text = jobData!!.requiredSkills[1].skillName else tvRequiredSkill2.visibility = View.GONE
        if (jobData!!.requiredSkills.size == 3) tvRequiredSkill3.text = jobData!!.requiredSkills[2].skillName else tvRequiredSkill3.visibility = View.GONE

        tvImportantNotes1.text = jobData.importantNotes[0]
        if (jobData.importantNotes.size == 2) tvImportantNotes2.text = jobData.importantNotes[1] else tvImportantNotes2.visibility = View.GONE
        if (jobData.importantNotes.size == 3) tvImportantNotes3.text = jobData.importantNotes[2] else tvImportantNotes3.visibility = View.GONE

    }

    override fun displayEmptyViewPod() {
        coordinatorLayout.visibility = View.GONE
        val emptyViewPod = vpEmpty as EmptyViewPod
        emptyViewPod.visibility = View.VISIBLE
        emptyViewPod.setEmptyData(R.drawable.notdatafound, MMKuNyiConstants.CANNOT_FETCH_JOB_DETAILS)
    }


}
