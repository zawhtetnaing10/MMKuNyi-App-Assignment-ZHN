package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.WhyShouldHireVO

@Dao
interface WhyShouldHireDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWhyShouldHire(vararg whyShouldHire: WhyShouldHireVO)
}