package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.ApplicantVO

@Dao
interface ApplicantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApplicants(vararg applicants: ApplicantVO)

    @Query("SELECT * FROM applicant WHERE seeker_id = :seekerId")
    fun getAllApplicantById(seekerId: Int): ApplicantVO

    @Query("SELECT * FROM applicant WHERE job_post_id=:jobPostId")
    fun getApplicantsByJobPostId(jobPostId: Int):List<ApplicantVO>
}