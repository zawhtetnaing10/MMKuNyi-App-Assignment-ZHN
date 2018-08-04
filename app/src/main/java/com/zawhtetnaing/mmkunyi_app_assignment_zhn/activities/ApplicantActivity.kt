package com.zawhtetnaing.mmkunyi_app_assignment_zhn.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.adapters.ApplicantAdapter
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.models.JobsModel
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO
import kotlinx.android.synthetic.main.activity_applicant.*

class ApplicantActivity : BaseActivity() {

    companion object {
        const val APPLICANTS_EXTRA_KEY = "applicants"
    }

    private lateinit var mJobData: JobVO
    private lateinit var mApplicantAdapter: ApplicantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applicant)

        setSupportActionBar(personAppliedToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val jobPostId = intent.getIntExtra(APPLICANTS_EXTRA_KEY, 0)
        mJobData = JobsModel.getInstance().findJobById(jobPostId)!!

        mApplicantAdapter = ApplicantAdapter(this)
        mApplicantAdapter.setNewData(mJobData.applicants)
        rvApplicants.adapter = mApplicantAdapter
        rvApplicants.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }
}
