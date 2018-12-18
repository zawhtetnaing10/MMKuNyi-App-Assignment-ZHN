package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "interested")
class InterestedVO(
        @SerializedName("interestedTimeStamp")
        @ColumnInfo(name = "interested_time_stamp")
        var interestedTimeStamp: String = "",

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

        @ColumnInfo(name = "job_post_id")
        var jobPostId:Int = 0)