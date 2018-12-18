package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "why_should_hire")
class WhyShouldHireVO(
        @SerializedName("msg")
        @ColumnInfo(name = "msg")
        var message: String = "",

        @SerializedName("timestamp")
        @ColumnInfo(name = "time_stamp")
        var timeStamp: String = "",

        @SerializedName("whileShouldHireId")
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "why_should_hire_id")
        var whyShouldHireId: Int = 0,

        @ColumnInfo(name = "seeker_id")
        var seekerId: Int = 0)
