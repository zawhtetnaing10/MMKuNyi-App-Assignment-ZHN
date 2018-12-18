package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "relevant")
class RelevantVO(
        @SerializedName("relevancyPercentage")
        @ColumnInfo(name = "relevancy_percentage")
        var relevancyPercantage: Double = 0.0,

        @SerializedName("seekerId")
        @ColumnInfo(name = "seeker_id")
        @PrimaryKey
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

        @SerializedName("whyRelevant")
        @ColumnInfo(name = "why_relevant")
        var whyRelevant: String = "",

        @ColumnInfo(name = "job_post_id")
        var jobPostId: Int = 0

)