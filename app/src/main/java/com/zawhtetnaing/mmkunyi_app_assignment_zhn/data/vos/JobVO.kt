package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import com.google.gson.annotations.SerializedName

class JobVO(@SerializedName("applicant") val applicants: List<ApplicantVO> = arrayListOf(),
            @SerializedName("availablePostCount") val availablePostCount: Int = 0,
            @SerializedName("email") val email: String = "",
            @SerializedName("fullDesc") val fullDesc: String = "",
            @SerializedName("genderForJob") val genderForJob: Int = 0,
            @SerializedName("images") val images: List<String> = arrayListOf(),
            @SerializedName("importantNotes") val importantNotes: List<String> = arrayListOf(),
            @SerializedName("interested") val interested: List<InterestedVO> = arrayListOf(),
            @SerializedName("isActive") val isActive: Boolean = false,
            @SerializedName("jobDuration") val jobDuration: JobDurationVO? = null,
            @SerializedName("jobPostId") val jobPostId: Int = 0,
            @SerializedName("jobTags") val jobTags: List<JobTagVO> = arrayListOf(),
            @SerializedName("location") val location: String = "",
            @SerializedName("makeMoneyRating") val makeMoneyRating: Int = 0,
            @SerializedName("offerAmount") val offerAmount: OfferAmountVO? = null,
            @SerializedName("phoneNo") val phoneNo: String = "",
            @SerializedName("postClosedDate") val postClosedDate: String = "",
            @SerializedName("postedDate") val postedDate: String = "",
            @SerializedName("relevant") val relevant: List<RelevantVO> = arrayListOf(),
            @SerializedName("requiredSkill") val requiredSkills: List<SeekerSkillVO> = arrayListOf(),
            @SerializedName("shortDesc") val shortDesc: String = "",
            @SerializedName("viewed") val viewed: List<ViewdVO> = arrayListOf())
