package com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import android.location.Address
import android.location.Geocoder
import android.support.design.widget.Snackbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.models.JobsModel
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.views.JobDetailsView
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.utils.MMKuNyiConstants
import kotlinx.android.synthetic.main.activity_job_details.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.IOException

class JobDetailsPresenter : BasePresenter<JobDetailsView>() {

    override fun initPresenter(mView: JobDetailsView) {
        super.initPresenter(mView)
    }

    fun onUIReady(jobPostId: Int): MutableLiveData<JobVO> {
        return JobsModel.getInstance().findJobByIdLD(jobPostId)
    }
}