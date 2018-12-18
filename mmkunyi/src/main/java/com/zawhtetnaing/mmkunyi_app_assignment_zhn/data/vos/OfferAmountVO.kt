package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "offer_amount")
class OfferAmountVO(

        @ColumnInfo(name = "offer_amount_id")
        @PrimaryKey(autoGenerate = true)
        var offerAmountId: Int = 0,

        @SerializedName("amount")
        @ColumnInfo(name = "amount")
        var offerAmount: Int = 0,

        @SerializedName("duration")
        @ColumnInfo(name = "duration")
        var duration: String = "")