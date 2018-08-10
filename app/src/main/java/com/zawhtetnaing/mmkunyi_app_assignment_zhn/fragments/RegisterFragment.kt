package com.zawhtetnaing.mmkunyi_app_assignment_zhn.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates.RegisterDelegate
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment() {

    private lateinit var mDelegate: RegisterDelegate

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        view.btnAlreadyLoggedIn.setOnClickListener {
            mDelegate.onTapAlreadyLoggedIn()
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mDelegate = context as RegisterDelegate
    }
}