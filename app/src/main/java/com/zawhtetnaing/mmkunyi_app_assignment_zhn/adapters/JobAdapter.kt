package com.zawhtetnaing.mmkunyi_app_assignment_zhn.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates.JobDelegate
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.holders.BaseJobViewHolder
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.holders.BaseViewHolder
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.holders.JobViewHolder

class JobAdapter(var mDelegate: JobDelegate, context: Context) : BaseRecyclerAdapter<BaseJobViewHolder, JobVO>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseJobViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.view_holder_job, parent, false)
        return JobViewHolder(view, mDelegate)
    }
}