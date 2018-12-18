package com.zawhtetnaing.mmkunyi_app_assignment_zhn.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
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
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.presenters.JobListPresenter
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.views.JobListView
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.utils.MMKuNyiConstants
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.pods.AccountControlViewPod
import kotlinx.android.synthetic.main.activity_job_list.*

class JobListActivity : BaseActivity(), JobListView {


    lateinit var mRecyclerAdapter: JobAdapter
    lateinit var mPresenter: JobListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        mPresenter = ViewModelProviders.of(this).get(JobListPresenter::class.java)
        mPresenter.initPresenter(this)

        setSupportActionBar(jobListtoolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)

        mRecyclerAdapter = JobAdapter(mPresenter, applicationContext)
        rvJobList.adapter = mRecyclerAdapter
        rvJobList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter.onSwipeGesture()
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

        val vpAccountControl = navigationView.getHeaderView(0) as AccountControlViewPod
        vpAccountControl.setDelegate(mPresenter)

        if(isNetworkAvailable()){
            mPresenter.uiReadyAndNetworkAvailable()
        }else{

        }

        mPresenter.getLiveData().also {
            it.observe(this, Observer<List<JobVO>> {
                displayJobList(it!!)
            })
        }

        mPresenter.getRefreshLiveData().also {
            it.observe(this, Observer<List<JobVO>> {
                displayJobListAfterRefreshed(it!!)
            })
        }

        mPresenter.getErrorLiveData().also {
            it.observe(this, this)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return false
    }

    override fun launchJobDetailsScreen(job: JobVO) {
        val intent = JobDetailsActivity.getIntent(applicationContext, job.jobPostId)
        startActivity(intent)
    }

    override fun launchPersonsAppliedScreen(job: JobVO) {
        val intent = Intent(applicationContext, ApplicantActivity::class.java)
        intent.putExtra(ApplicantActivity.APPLICANTS_EXTRA_KEY, job.jobPostId)
        startActivity(intent)
    }

    override fun launchLoginScreen() {
        val intent = Intent(applicationContext, AccountControlActivity::class.java)
        intent.putExtra(AccountControlActivity.ACTION_TYPE, AccountControlActivity.ACTION_TYPE_LOGIN)
        startActivity(intent)
    }

    override fun launchRegisterScreen() {
        val intent = Intent(applicationContext, AccountControlActivity::class.java)
        intent.putExtra(AccountControlActivity.ACTION_TYPE, AccountControlActivity.ACTION_TYPE_REGISTER)
        startActivity(intent)
    }


    private fun displayJobList(jobList: List<JobVO>) {
        swipeRefreshLayout.isRefreshing = false
        vpEmpty.visibility = View.GONE
        mRecyclerAdapter.appendNewData(jobList)
    }

    override fun displayEmptyViewPod() {
        swipeRefreshLayout.isRefreshing = false
        if (mRecyclerAdapter.itemCount <= 0) {
            vpEmpty.visibility = View.VISIBLE
        } else {
            vpEmpty.visibility = View.GONE
        }
    }

    private fun displayJobListAfterRefreshed(jobList: List<JobVO>) {
        swipeRefreshLayout.isRefreshing = false
        vpEmpty.visibility = View.GONE
        mRecyclerAdapter.setNewData(jobList)
    }

}
