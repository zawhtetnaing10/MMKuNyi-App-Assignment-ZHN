package com.zawhtetnaing.mmkunyi_app_assignment_zhn.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.mvp.presenters.BasePresenter

abstract class BaseActivity : AppCompatActivity(), Observer<String> {

    override fun onChanged(errorMessage: String?) {
        displayEmptyViewPod()
    }

    open fun displayEmptyViewPod() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    protected fun isNetworkAvailable(): Boolean {
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).also {
            return (it.activeNetworkInfo != null && it.activeNetworkInfo.isConnected)
        }
        return false
    }
}
