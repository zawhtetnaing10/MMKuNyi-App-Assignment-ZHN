package com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates.BeforeLoginDelegate
import kotlinx.android.synthetic.main.view_pod_before_login.view.*

class BeforeLogInViewPod : RelativeLayout {

    lateinit var mDelegate: BeforeLoginDelegate

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()

        btnLogin.setOnClickListener {
            mDelegate.onTapLogin()
        }

        btnRegister.setOnClickListener {
            mDelegate.onTapRegister()
        }

    }

    fun setDelegate(beforeLoginDelegate: BeforeLoginDelegate) {
        mDelegate = beforeLoginDelegate
    }
}