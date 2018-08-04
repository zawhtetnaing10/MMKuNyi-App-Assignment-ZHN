package com.zawhtetnaing.mmkunyi_app_assignment_zhn.view.holders

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.R
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.ApplicantVO

import kotlinx.android.synthetic.main.view_holder_applicant.view.*

class ApplicantViewHolder(itemView: View?) : BaseApplicantViewHolder(itemView) {

    override fun bindData(applicantData: ApplicantVO) {

        Glide.with(itemView!!.ivApplicant.context)
                .load(R.drawable.applicant_1)
                .apply(RequestOptions.circleCropTransform())
                .into(itemView!!.ivApplicant)

        itemView!!.tvApplicantName.text = applicantData.seekerName
        itemView!!.tvApplicantSkill1.text = "-  ${applicantData.seekerSkill[0].skillName}"
        if (applicantData.seekerSkill.size == 2) itemView!!.tvApplicantSkill2.text = "-  ${applicantData.seekerSkill[1].skillName}" else itemView!!.tvApplicantSkill2.visibility = View.INVISIBLE
        if (applicantData.seekerSkill.size == 3) itemView!!.tvApplicantSkill3.text = "-  ${applicantData.seekerSkill[2].skillName}" else itemView!!.tvApplicantSkill3.visibility = View.INVISIBLE

    }
}