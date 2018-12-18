package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "applicant")
class ApplicantVO(
        @SerializedName("appliedDate")
        @ColumnInfo(name = "applied_date")
        var appliedDate: String = "",

        @SerializedName("canLowerOfferAmount")
        @ColumnInfo(name = "can_lower_offer_amount")
        var canLowerOfferAmount: Boolean = false,

        @SerializedName("seekerId")
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "seeker_id")
        var seekerId: Int = 0,

        @SerializedName("seekerName")
        @ColumnInfo(name = "seeker_name")
        var seekerName: String = "",

        @SerializedName("seekerProfilePicUrl")
        @ColumnInfo(name = "seeker_profile_pic_url")
        var seekerProfilePicUrl: String = "",

        @SerializedName("seekerSkill")
        @Ignore
        var seekerSkill: List<SeekerSkillVO> = arrayListOf(),

        @SerializedName("whyShouldHire")
        @Ignore
        var whyShouldHire: List<WhyShouldHireVO> = arrayListOf(),

        @ColumnInfo(name = "job_post_id")
        var  jobPostId: Int = 0)