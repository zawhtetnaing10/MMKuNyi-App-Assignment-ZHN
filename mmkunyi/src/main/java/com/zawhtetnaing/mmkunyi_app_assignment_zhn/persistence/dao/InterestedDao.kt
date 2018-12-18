package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.ApplicantVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.InterestedVO

@Dao
interface InterestedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInterested(vararg interested: InterestedVO)

    @Query("SELECT * FROM interested WHERE job_post_id = :jobPostId")
    fun getInterestedByJobPostID(jobPostId: Int):List<InterestedVO>
}