package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.typeconverters

import android.arch.persistence.room.TypeConverter
import java.lang.StringBuilder

class StringListToCommaSeparatedStringTypeConverter {

    @TypeConverter
    fun getStringList(stringCommaSeparated: String): List<String> {
        return stringCommaSeparated.split(",")
    }

    @TypeConverter
    fun getString(stringList: List<String>): String {
        val stringBuilder = StringBuilder()
        for (string in stringList) {
            stringBuilder.append(string).append(",")
        }
        if (stringBuilder.isNotEmpty()) {
            stringBuilder.deleteCharAt(stringBuilder.length - 1)
        }
        return stringBuilder.toString()
    }

}