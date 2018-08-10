package com.zawhtetnaing.mmkunyi_app_assignment_zhn.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates.LogInDelegate
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    lateinit var mDelegate: LogInDelegate

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.btnDontHaveAccountYet.setOnClickListener {
            mDelegate.onTapDontHaveAccountYet()
        }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mDelegate = context as LogInDelegate
    }
}
