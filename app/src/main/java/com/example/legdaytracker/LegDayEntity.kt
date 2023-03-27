package com.example.legdaytracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leg_day_table")
data class LegDayEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "squat") val squat: String?,
    @ColumnInfo(name = "legExtension") val legExtension: String?,
    @ColumnInfo(name = "legCurl") val legCurl: String?
)

