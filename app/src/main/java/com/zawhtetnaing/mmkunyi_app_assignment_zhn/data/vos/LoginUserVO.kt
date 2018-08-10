package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import com.google.gson.annotations.SerializedName

class LoginUserVO(@SerializedName("") val userId: Int = 0,
                  @SerializedName("") val name: String = "",
                  @SerializedName("") val email: String = "",
                  @SerializedName("") val phoneNo: String = "",
                  @SerializedName("") val profileUrl: String = "",
                  @SerializedName("") val coverUrl: String = "")
