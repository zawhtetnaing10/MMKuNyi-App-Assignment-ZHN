package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import com.google.gson.annotations.SerializedName

class RelevantVO(@SerializedName("relevancyPercentage") val relevancyPercantage: Double = 0.0,
                 @SerializedName("seekerId") val seekerId: Int = 0,
                 @SerializedName("seekerName") val seekerName: String = "",
                 @SerializedName("seekerProfilePicUrl") val seekerProfilePicUrl: String = "",
                 @SerializedName("seekerSkill") val seekerSkill: List<SeekerSkillVO> = arrayListOf(),
                 @SerializedName("whyRelevant") val whyRelevant: String = "")