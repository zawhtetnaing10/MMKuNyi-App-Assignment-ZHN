package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.RelevantVO

@Dao
interface RelevantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRelevants(vararg relevants: RelevantVO)

    @Query("SELECT * FROM relevant WHERE job_post_id = :jobPostId")
    fun getRelevantByJobPostId(jobPostId:Int):List<RelevantVO>
}