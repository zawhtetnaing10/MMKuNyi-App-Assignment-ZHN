package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import com.google.gson.annotations.SerializedName

class JobTagVO(@SerializedName("desc") val desc: String = "",
               @SerializedName("jobCountWithTag") val jobCountWithTag: Int = 0,
               @SerializedName("tag") val tag: String = "",
               @SerializedName("tagId") val tagId: Int = 0)