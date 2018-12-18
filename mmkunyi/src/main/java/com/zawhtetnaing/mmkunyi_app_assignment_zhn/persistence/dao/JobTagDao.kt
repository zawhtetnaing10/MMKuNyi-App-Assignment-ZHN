package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobTagVO

@Dao
interface JobTagDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJobTags(vararg jobTags: JobTagVO): Array<Long>

    @Query("SELECT * FROM job_tag WHERE job_post_id = :jobPostId")
    fun getJobTagsByJobPostId(jobPostId: Int):List<JobTagVO>
}