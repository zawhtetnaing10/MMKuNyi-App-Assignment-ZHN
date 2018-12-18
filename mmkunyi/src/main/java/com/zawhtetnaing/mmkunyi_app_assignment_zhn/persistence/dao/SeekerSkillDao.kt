package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.SeekerSkillVO

@Dao
interface SeekerSkillDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSeekerSkills(vararg seekerSkills: SeekerSkillVO)

    @Query("SELECT * FROM seeker_skill WHERE job_post_id=:jobPostId")
    fun getSeekerSkillByJobPostId(jobPostId: Int): List<SeekerSkillVO>
}