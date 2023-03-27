package com.example.legdaytracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LegDayDao {
    @Query("SELECT * FROM leg_day_table")
    fun getAll(): Flow<List<LegDayEntity>>

    @Insert
    fun insertAll(legDays: List<LegDayEntity>)

    @Insert
    fun insert(legDay: LegDayEntity)

    @Query("DELETE FROM leg_day_table")
    fun deleteAll()
}

