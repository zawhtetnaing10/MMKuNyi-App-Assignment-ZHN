package com.zawhtetnaing.mmkunyi_app_assignment_zhn.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.ApplicantVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.holders.ApplicantViewHolder

class ApplicantAdapter(context: Context) : BaseRecyclerAdapter<ApplicantViewHolder, ApplicantVO>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicantViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.view_holder_applicant, parent, false)
        return ApplicantViewHolder(view)
    }
}