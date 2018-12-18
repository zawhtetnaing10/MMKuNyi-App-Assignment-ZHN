package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJob(vararg jobs: JobVO)

    @Query("SELECT * FROM job WHERE job_post_id=:jobPostId ")
    fun findJobById(jobPostId: Int): LiveData<JobVO>
}