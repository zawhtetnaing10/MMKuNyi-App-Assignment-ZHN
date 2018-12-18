package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.JobVO
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.OfferAmountVO

@Dao
interface OfferAmountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOfferAmounts(vararg offerAmounts: OfferAmountVO)
}