package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.ApplicantVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobDurationVO

@Dao
interface JobDurationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJobDuration(vararg jobDurations: JobDurationVO)
}