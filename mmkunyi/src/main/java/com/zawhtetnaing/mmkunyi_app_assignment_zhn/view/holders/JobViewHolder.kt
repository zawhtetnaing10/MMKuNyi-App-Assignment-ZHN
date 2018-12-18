package com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.delegates.JobDelegate
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.utils.MMKuNyiConstants
import kotlinx.android.synthetic.main.view_holder_job.view.*

class JobViewHolder(itemView: View?, private var mDelegate: JobDelegate) : BaseJobViewHolder(itemView) {

    init {
        itemView!!.setOnClickListener {
            mDelegate.onTapJobs(mData)
        }
        itemView!!.tvNumberOfPersonApplied.setOnClickListener {
            mDelegate.onTapPersonsApplied(mData)
        }
    }

    override fun bindData(data: JobVO) {
        super.bindData(data)
        itemView.tvShortJobDescription.text = data.shortDesc
        itemView.tvFullJobDescription.text = data.fullDesc
        itemView.tvSalary.text = data.offerAmount!!.offerAmount.toString()
        itemView.tvSalaryDuration.text = data.offerAmount!!.duration
        itemView.tvLocation.text = data.location
        itemView.tvNumberOfPersonApplied.text = MMKuNyiConstants.numberOfPeopleApplied(data.applicants.size)
    }

}
