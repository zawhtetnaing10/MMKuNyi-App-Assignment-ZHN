package com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos

import com.google.gson.annotations.SerializedName

class OfferAmountVO(@SerializedName("amount") val offerAmount: Int = 0,
                    @SerializedName("duration") val duration: String = "")