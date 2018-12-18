package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "seeker_skill")
class SeekerSkillVO(

        @SerializedName("skillId")
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "skill_id")
        var skillId: Int = 0,

        @SerializedName("skillName")
        @ColumnInfo(name = "skill_name")
        var skillName: String = "",

        @ColumnInfo(name = "applicant_id")
        var applicantId: Int = 0,

        @ColumnInfo(name = "interested_id")
        var interestedId: Int = 0,

        @ColumnInfo(name = "relevant_id")
        var relevantId: Int = 0,

        @ColumnInfo(name = "viewed_id")
        var viewedId: Int = 0,

        @ColumnInfo(name = "job_post_id")
        var jobPostId: Int = 0) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SeekerSkillVO

        if (skillId != other.skillId) return false

        return true
    }

    override fun hashCode(): Int {
        return skillId
    }
}