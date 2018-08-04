package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import com.google.gson.annotations.SerializedName

class WhyShouldHireVO(@SerializedName("msg") val message: String = "",
                      @SerializedName("timestamp") val timeStamp: String = "",
                      @SerializedName("whileShouldHireId") val whyShouldHireId: Int = 0)
