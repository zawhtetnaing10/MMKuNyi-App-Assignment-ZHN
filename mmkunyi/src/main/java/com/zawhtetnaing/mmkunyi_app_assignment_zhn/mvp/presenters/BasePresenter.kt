package com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.presenters

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.EventLog
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.views.BaseView
import org.greenrobot.eventbus.EventBus
import rx.subjects.PublishSubject

abstract class BasePresenter<V : BaseView> : ViewModel() {

    protected lateinit var errorLD: MutableLiveData<String>

    protected var mView: V? = null

    open fun initPresenter(mView: V) {
        this.mView = mView
        errorLD = MutableLiveData()
    }

   fun getErrorLiveData(): MutableLiveData<String> = errorLD

}