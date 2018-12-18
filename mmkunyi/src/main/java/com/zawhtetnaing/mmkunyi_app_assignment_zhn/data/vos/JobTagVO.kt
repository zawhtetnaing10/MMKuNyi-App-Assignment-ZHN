package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "job_tag")
class JobTagVO(
        @SerializedName("desc")
        @ColumnInfo(name = "desc")
        var desc: String = "",

        @SerializedName("jobCountWithTag")
        @ColumnInfo(name = "job_count_with_tag")
        var jobCountWithTag: Int = 0,

        @SerializedName("tag")
        @ColumnInfo(name = "tag")
        var tag: String = "",

        @SerializedName("tagId")
        @ColumnInfo(name = "tag_id")
        @PrimaryKey(autoGenerate = true)
        var tagId: Int = 0,

        @ColumnInfo(name = "job_post_id")
        var jobPostId: Int = 0)