package com.example.legdaytrackerBetter

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LegDayDao {
    @Query("SELECT * FROM leg_day_table")
    fun getAll(): Flow<List<LegDayEntity>>

    @Query("SELECT squat FROM leg_day_table")
    fun getSquat(): List<Int>

    @Query("SELECT legExtension FROM leg_day_table")
    fun getLegExt(): List<Int>

    @Query("SELECT legCurl FROM leg_day_table")
    fun getLegCurl(): List<Int>

    @Insert
    fun insertAll(legDays: List<LegDayEntity>)

    @Insert
    fun insert(legDay: LegDayEntity)

    @Query("DELETE FROM leg_day_table")
    fun deleteAll()
}

