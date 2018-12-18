package com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.data.vos.*
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.dao.*
import com.zawhtetnaing.mmkunyi_app_assignment_zhn.persistence.typeconverters.StringListToCommaSeparatedStringTypeConverter

@Database(entities = arrayOf(ApplicantVO::class,
        InterestedVO::class,
        JobDurationVO::class,
        JobTagVO::class,
        JobVO::class,
        OfferAmountVO::class,
        RelevantVO::class,
        SeekerSkillVO::class,
        ViewdVO::class,
        WhyShouldHireVO::class), version = 1, exportSchema = false
)
abstract class MMKunyiDB : RoomDatabase() {
    companion object {
        const val DB_NAME = "MM-Kunyi.DB"
        var INSTANCE: MMKunyiDB? = null

        fun getMMKunyiDB(context: Context): MMKunyiDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, MMKunyiDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE!!
        }
    }

    abstract fun applicantDao(): ApplicantDao
    abstract fun interestedDao(): InterestedDao
    abstract fun jobDao(): JobDao
    abstract fun jobDurationDao(): JobDurationDao
    abstract fun offerAmountDao(): OfferAmountDao
    abstract fun jobTagDao(): JobTagDao
    abstract fun relevantDao(): RelevantDao
    abstract fun seekerSkillDao(): SeekerSkillDao
    abstract fun viewedDao(): ViewedDao
    abstract fun whyShouldHireDao(): WhyShouldHireDao

}