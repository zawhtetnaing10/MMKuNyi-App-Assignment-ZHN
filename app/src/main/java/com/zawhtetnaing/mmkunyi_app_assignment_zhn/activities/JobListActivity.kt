package com.zawhtetnaing.mmkunyi_app_assignment_zhn.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.adapters.JobAdapter
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.models.JobsModel
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates.JobDelegate
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.events.ApiErrorEvent
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.events.SuccessForceRefreshGetJobsEvent
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.events.SuccessGetJobsEvent
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.utils.MMKuNyiConstants
import kotlinx.android.synthetic.main.activity_job_list.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class JobListActivity : AppCompatActivity(), JobDelegate {

    lateinit var mRecyclerAdapter: JobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        setSupportActionBar(jobListtoolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)

        mRecyclerAdapter = JobAdapter(this, applicationContext)
        rvJobList.adapter = mRecyclerAdapter
        rvJobList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener {
            JobsModel.getInstance().forceRefreshLoadJobs()
        }

        rvJobList.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            var isListEndReached: Boolean = false

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = rvJobList.layoutManager.childCount
                val totalItemCount = rvJobList.layoutManager.itemCount
                val pastVisibleItemCount = (rvJobList.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if ((visibleItemCount + pastVisibleItemCount) < totalItemCount) isListEndReached = false

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, scrollState: Int) {
                super.onScrollStateChanged(recyclerView, scrollState)
                if ((scrollState == RecyclerView.SCROLL_STATE_IDLE
                                && (rvJobList.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() == mRecyclerAdapter.itemCount - 1) && !
                        isListEndReached) {

                    isListEndReached = true
                    //JobsModel.getInstance().loadNewJobs()
                }
            }
        })

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_profile -> {
                    val intent = Intent(applicationContext, ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.menu_about_us -> Snackbar.make(navigationView, MMKuNyiConstants.TAPPED_ABOUT_US, Snackbar.LENGTH_LONG).show()
                R.id.menu_chat -> Snackbar.make(navigationView, MMKuNyiConstants.TAPPED_CHAT, Snackbar.LENGTH_LONG).show()
            }

            for (menuItemIndex in 0 until navigationView.menu.size()) {
                navigationView.menu.getItem(menuItemIndex).isChecked = false
            }
            it.isChecked = true
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

        JobsModel.getInstance().loadNewJobs()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return false
    }

    override fun onTapJobs(jobData: JobVO?) {
        val intent = Intent(applicationContext, JobDetailsActivity::class.java)
        intent.putExtra(JobDetailsActivity.JOB_ID_EXTRA, jobData!!.jobPostId)
        startActivity(intent)
    }

    override fun onTapPersonsApplied(jobData: JobVO?) {
        val intent = Intent(applicationContext, ApplicantActivity::class.java)
        intent.putExtra(ApplicantActivity.APPLICANTS_EXTRA_KEY, jobData!!.jobPostId)
        startActivity(intent)
    }


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetJobsEvent(event: SuccessGetJobsEvent) {
        swipeRefreshLayout.isRefreshing = false
        vpEmpty.visibility = View.GONE
        mRecyclerAdapter.appendNewData(event.jobs)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessForceRefreshedGetJobsEvent(event: SuccessForceRefreshGetJobsEvent) {
        swipeRefreshLayout.isRefreshing = false
        vpEmpty.visibility = View.GONE
        mRecyclerAdapter.setNewData(event.jobs)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFailureGetJobsEvent(event: ApiErrorEvent) {
        swipeRefreshLayout.isRefreshing = false
        if (mRecyclerAdapter.itemCount <= 0) {
            vpEmpty.visibility = View.VISIBLE
        } else {
            vpEmpty.visibility = View.GONE
        }
    }

}
