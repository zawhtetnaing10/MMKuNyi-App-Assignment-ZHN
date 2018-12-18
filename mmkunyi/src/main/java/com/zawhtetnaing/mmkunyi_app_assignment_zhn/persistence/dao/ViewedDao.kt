package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.ViewdVO

@Dao
interface ViewedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertViewed(vararg viewed: ViewdVO)

    @Query("SELECT * FROM viewed WHERE job_post_id = :jobPostId")
    fun getViewdByJobPostId(jobPostId: Int):List<ViewdVO>
}