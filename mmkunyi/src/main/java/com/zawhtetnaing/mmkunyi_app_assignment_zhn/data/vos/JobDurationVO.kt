package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "job_duration")
class JobDurationVO(
        @ColumnInfo(name = "job_duration_id")
        @PrimaryKey(autoGenerate = true)
        var jobDurationId: Int = 0,

        @SerializedName("jobEndDate")
        @ColumnInfo(name = "job_end_date")
        var jobEndDate: String = "",

        @SerializedName("jobStartDate")
        @ColumnInfo(name = "job_start_date")
        var jobStartDate: String = "",

        @SerializedName("totalWorkingDays")
        @ColumnInfo(name = "total_working_days")
        var totalWorkingDays: Int = 0,

        @SerializedName("workingDaysPerWeek")
        @ColumnInfo(name = "working_days_per_week")
        var workingDaysPerWeek: Int = 0,

        @SerializedName("workingHoursPerDay")
        @ColumnInfo(name = "working_hours_per_day")
        var workingHoursPerDay: Double = 0.0)