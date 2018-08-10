package com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.pods

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.models.LoginUserModel
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates.BeforeLoginDelegate
import kotlinx.android.synthetic.main.view_pod_account_control.view.*

class AccountControlViewPod : FrameLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (LoginUserModel.getInstance().isUserLoggedIn()) {
            vpLogin.visibility = View.VISIBLE
            vpBeforeLogin.visibility = View.GONE
        } else {
            vpLogin.visibility = View.GONE
            vpBeforeLogin.visibility = View.VISIBLE
        }
    }

    fun setDelegate(beforeLoginDelegate: BeforeLoginDelegate) {
        (vpBeforeLogin as BeforeLogInViewPod).setDelegate(beforeLoginDelegate)
    }

}