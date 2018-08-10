package com.zawhtetnaing.mmkunyi_app_assignment_zhn.activities

import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.models.JobsModel
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.utils.MMKuNyiConstants
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.pods.EmptyViewPod
import kotlinx.android.synthetic.main.activity_job_details.*
import java.io.IOException

class JobDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var mJobData: JobVO? = null

    companion object {
        const val JOB_ID_EXTRA = "jobIDExtra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_details)

        val jobId: Int = intent.getIntExtra(JOB_ID_EXTRA, 0)
        mJobData = JobsModel.getInstance().findJobById(jobId)

        setSupportActionBar(jobDetailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        jobDetailsAppbar.addOnOffsetChangedListener { _, verticalOffset ->
            if (verticalOffset == 0) {
                jobDetailsCollapsingToolbarLayout.isTitleEnabled = false
                jobDetailsToolbar.title = ""
            } else {
                jobDetailsCollapsingToolbarLayout.isTitleEnabled = true
            }
        }

        mJobData?.let {
            bindData(it)
        } ?: run {
            coordinatorLayout.visibility = View.GONE
            val emptyViewPod = vpEmpty as EmptyViewPod
            emptyViewPod.visibility = View.VISIBLE
            emptyViewPod.setEmptyData(R.drawable.notdatafound, MMKuNyiConstants.CANNOT_FETCH_JOB_DETAILS)
        }
    }

    private fun bindData(jobData: JobVO?) {
        tvjobDetailsDescription.text = jobData!!.shortDesc
        tvjobDetailsFullDescription.text = jobData!!.fullDesc
        tvjobDetailsLocation.text = jobData!!.location
        tvjobDetailsPayment.text = MMKuNyiConstants.offerAmountAndDuration(jobData.offerAmount?.offerAmount, jobData.offerAmount?.duration)
        tvjobDetailsPeriod.text = MMKuNyiConstants.totalWorkingDays(jobData.jobDuration?.totalWorkingDays)
        tvjobDetailsVacancy.text = MMKuNyiConstants.vacancy(jobData.availablePostCount)

        tvRequiredSkill1.text = jobData.requiredSkills[0].skillName
        if (jobData.requiredSkills.size == 2) tvRequiredSkill2.text = jobData.requiredSkills[1].skillName else tvRequiredSkill2.visibility = View.GONE
        if (jobData.requiredSkills.size == 3) tvRequiredSkill3.text = jobData.requiredSkills[2].skillName else tvRequiredSkill3.visibility = View.GONE

        tvImportantNotes1.text = jobData.importantNotes[0]
        if (jobData.importantNotes.size == 2) tvImportantNotes2.text = jobData.importantNotes[1] else tvImportantNotes2.visibility = View.GONE
        if (jobData.importantNotes.size == 3) tvImportantNotes3.text = jobData.importantNotes[2] else tvImportantNotes3.visibility = View.GONE
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val geoCoder = Geocoder(this)
        lateinit var addressList: MutableList<Address>

        mJobData?.location?.let {
            try {
                addressList = geoCoder.getFromLocationName(it, 1)
                if (addressList.size != 0) {
                    val location = LatLng(addressList[0].latitude, addressList[0].longitude)
                    mMap.addMarker(MarkerOptions().position(location)).title = mJobData?.location
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, MMKuNyiConstants.GOOGLE_MAP_ZOOM))
                } else {
                    Snackbar.make(tvJobDetailsRequirementsTitle, MMKuNyiConstants.CANNOT_FIND_POSITION, Snackbar.LENGTH_INDEFINITE).show()
                }

            } catch (e: IOException) {
            }
        }
    }
}
