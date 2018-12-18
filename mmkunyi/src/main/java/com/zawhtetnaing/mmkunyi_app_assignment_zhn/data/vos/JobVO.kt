package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.typeconverters.StringListToCommaSeparatedStringTypeConverter

@Entity(tableName = "job")
@TypeConverters(StringListToCommaSeparatedStringTypeConverter::class)
class JobVO(
        @SerializedName("applicant")
        @Ignore
        var applicants: List<ApplicantVO> = arrayListOf(),

        @SerializedName("availablePostCount")
        @ColumnInfo(name = "available_post_count")
        var availablePostCount: Int = 0,

        @SerializedName("email")
        @ColumnInfo(name = "email")
        var email: String = "",

        @SerializedName("fullDesc")
        @ColumnInfo(name = "full_desc")
        var fullDesc: String = "",

        @SerializedName("genderForJob")
        @ColumnInfo(name = "gender_for_job")
        var genderForJob: Int = 0,

        @SerializedName("images")
        @ColumnInfo(name = "images")
        var images: List<String> = arrayListOf(),

        @SerializedName("importantNotes")
        @ColumnInfo(name = "important_notes")
        var importantNotes: List<String> = arrayListOf(),

        @SerializedName("interested")
        @Ignore
        var interested: List<InterestedVO> = arrayListOf(),

        @SerializedName("isActive")
        @ColumnInfo(name = "is_active")
        var isActive: Boolean = false,

        @SerializedName("jobDuration")
        @Ignore
        var jobDuration: JobDurationVO? = null,

        @SerializedName("jobPostId")
        @ColumnInfo(name = "job_post_id")
        @PrimaryKey(autoGenerate = true)
        var jobPostId: Int = 0,

        @SerializedName("jobTags")
        @Ignore
        var jobTags: List<JobTagVO> = arrayListOf(),

        @SerializedName("location")
        @ColumnInfo(name = "location")
        var location: String = "",

        @SerializedName("makeMoneyRating")
        @ColumnInfo(name = "make_monty_rating")
        var makeMoneyRating: Int = 0,

        @SerializedName("offerAmount")
        @Ignore
        var offerAmount: OfferAmountVO? = null,

        @SerializedName("phoneNo")
        @ColumnInfo(name = "phone_no")
        var phoneNo: String = "",

        @SerializedName("postClosedDate")
        @ColumnInfo(name = "post_closed_date")
        var postClosedDate: String = "",

        @SerializedName("postedDate")
        @ColumnInfo(name = "posted_date")
        var postedDate: String = "",

        @SerializedName("relevant")
        @Ignore
        var relevant: List<RelevantVO> = arrayListOf(),

        @SerializedName("requiredSkill")
        @Ignore
        var requiredSkills: List<SeekerSkillVO> = arrayListOf(),

        @SerializedName("shortDesc")
        @ColumnInfo(name = "short_desc")
        var shortDesc: String = "",

        @SerializedName("viewed")
        @Ignore
        var viewed: List<ViewdVO> = arrayListOf()) {

    @ColumnInfo(name = "job_duration_id")
    var jobDurationId: Int = 0
        get() {
            return jobDuration!!.jobDurationId
        }

    @ColumnInfo(name = "offer_amount_id")
    var offerAmountId: Int = 0
        get() {
            return offerAmount!!.offerAmountId
        }
}
